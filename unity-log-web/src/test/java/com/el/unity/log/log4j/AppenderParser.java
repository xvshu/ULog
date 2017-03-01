package com.el.unity.log.log4j;

import org.apache.log4j.Appender;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xvshu on 2016/6/17.
 */
public class AppenderParser extends DOMConfigurator {
    private Map<String, Appender> appenderBag = new HashMap<String, Appender>();
    private Document doc = null;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db = null;

    public AppenderParser() {}

    public void parse(String configFile) throws Exception {
        doc = getDocument(configFile);
        NodeList appenderList = doc.getElementsByTagName("appender");
        for (int t = 0; t < appenderList.getLength(); t++) {
            Node node = appenderList.item(t);
            NamedNodeMap map = node.getAttributes();
            Node attrNode = map.getNamedItem("name");
            if (getAppenderBag().get(attrNode.getNodeValue()) == null) {
//                Appender appender = parseAppender((Element) node);
//                getAppenderBag().put(attrNode.getNodeValue(), appender);
            }
        }
    }

    private Document getDocument(String configFile) throws Exception {
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        return db.parse(new File(configFile));
    }

    public static void main(String[] args) throws Exception {
        String configFile = "D:\\log4j.xml";
        AppenderParser config = new AppenderParser();
        config.parse(configFile);
        System.out.println(config.getAppenderBag());
    }

    public void setAppenderBag(Map<String, Appender> appenderBag) {
        this.appenderBag = appenderBag;
    }

    public Map<String, Appender> getAppenderBag() {
        return appenderBag;
    }
}
