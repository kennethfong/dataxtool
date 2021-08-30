package com.corolla.util;

import com.corolla.exception.CustomFileException;
import com.corolla.model.Datasource;
import com.corolla.model.Datasources;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.collections4.CollectionUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * @description:
 * @copyright:
 * @author: kan.feng
 * @version: 1.0
 * @date: 2021-08-30
 * @time: 20:00
 * </pre>
 */
public class DatasourceUtil {

    private static List<Datasource> datasourceList = new ArrayList<>();
    private static int nextNum = 1;

    private static final String SETTING_FILE_PATH = "/settings.xml";

    private static final String CONFIG_FILE_PATH = "/config";

    private static final String HOLE_SETTING_PATH = CONFIG_FILE_PATH + SETTING_FILE_PATH;

    private DatasourceUtil() {}

    public static void init() throws IOException {
        datasourceList = readDatasource();
        if (CollectionUtils.isNotEmpty(datasourceList)) {
            Collections.sort(datasourceList);
            nextNum = datasourceList.get(datasourceList.size() - 1).getIndex();
        }
    }

    /**
     * 不确定是否可以复用，暂时先创建对象
     * @return XStream
     */
    private static XStream getXStream() {
        XStream xstream = new XStream();
        xstream.allowTypes(new Class[] {Datasources.class, Datasource.class});
        xstream.alias("datasources", Datasources.class);
        xstream.autodetectAnnotations(Boolean.TRUE);
        return xstream;
    }

    private static List<Datasource> readDatasource() throws IOException {
        File file = prepareSettingFile();
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        XStream xstream = getXStream();
        try (InputStream inStream = DatasourceUtil.class.getResourceAsStream(HOLE_SETTING_PATH)) {
            Datasources entity = (Datasources) xstream.fromXML(inStream);
            return entity.getDatasourceList();
        }
    }

    public static synchronized int generateIndex() {
        return nextNum++;
    }

    public static List<Datasource> getDatasourceList() {
        return datasourceList;
    }

    public static void saveDatasource(Datasource datasource) throws IOException {
        File file = prepareSettingFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            datasourceList.add(datasource);
            Datasources object = new Datasources(datasourceList);
            XStream xStream = getXStream();
            xStream.toXML(object, writer);
        }
    }

    private static File prepareSettingFile() throws IOException {
        URL configUrl = DatasourceUtil.class.getResource(CONFIG_FILE_PATH);
        if (configUrl == null) {
            File configFile = new File(
                    DatasourceUtil.class.getResource("/").getFile() + CONFIG_FILE_PATH);
            if (!configFile.mkdir()) {
                throw new CustomFileException("无法创建文件夹，路径：" + configFile.getAbsolutePath());
            }
        }
        return createFile(
                DatasourceUtil.class.getResource("/").getFile() + CONFIG_FILE_PATH + SETTING_FILE_PATH);
    }

    private static File createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists() && !file.createNewFile()) {
            throw new CustomFileException("无法创建文件，路径：" + path);
        }
        return file;
    }
}
