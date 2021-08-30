package com.corolla;

import com.corolla.model.Datasource;
import com.corolla.model.Datasources;
import com.corolla.util.DocumentUtil;
import com.thoughtworks.xstream.XStream;
/*import org.dom4j.Document;
import org.dom4j.DocumentException;*/

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        /*String path = "settings.xml";
        Document doc = DocumentUtil.readXmlDocument(path);
        System.out.println(doc);*/

        readSettings();

    }

    public static void writeSettings() {
        XStream xstream = new XStream();
        xstream.allowTypes(new Class[] {Datasources.class, Datasource.class});
        xstream.alias("datasources", Datasources.class);
        xstream.autodetectAnnotations(Boolean.TRUE);
        String result = xstream.toXML(new Datasources());
        System.out.println(result);
    }

    public static void readSettings() {
        XStream xstream = new XStream();
        xstream.allowTypes(new Class[] {Datasources.class, Datasource.class});
        xstream.alias("datasources", Datasources.class);
        xstream.autodetectAnnotations(Boolean.TRUE);
        try {
            InputStream resource = Test.class.getResourceAsStream("/config/settings.xml");
            Datasources entity = (Datasources) xstream.fromXML(resource);
            List<Datasource> list = entity.getDatasourceList();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
