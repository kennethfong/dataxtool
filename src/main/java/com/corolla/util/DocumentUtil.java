package com.corolla.util;

//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;

import java.io.*;

public class DocumentUtil {

    private DocumentUtil() {}

    /*public static Document readXmlDocument(String filePath) throws DocumentException, IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            file.createNewFile();
            return writeEmptyDocument(filePath);
        }
        try (InputStream in = new FileInputStream(file)) {
            SAXReader reader = new SAXReader();
            return reader.read(in);
        }
    }

    private static Document writeEmptyDocument(String path) throws IOException {
        Document document = DocumentHelper.createDocument();
        document.addElement("datasources");
        writeXmlDocument(document, path);
        return document;
    }

    public static void writeXmlDocument(Document document, String path) throws IOException {
        XMLWriter writer = null;
        try (FileOutputStream fos = new FileOutputStream(path)) {
            writer = new XMLWriter(fos, OutputFormat.createPrettyPrint());
            writer.write(document);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }*/
}
