package com.iflytek.speech.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Xml结果解析类
 */
@SuppressWarnings("unused")
public class XmlParser {

    public static String parseNluResult(String xml) {
        StringBuilder buffer = new StringBuilder();
        try {
            // DOM builder
            DocumentBuilder domBuilder;
            // DOM doc
            Document domDoc;

            // init DOM
            DocumentBuilderFactory domFact = DocumentBuilderFactory.newInstance();
            domBuilder = domFact.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xml.getBytes());
            domDoc = domBuilder.parse(is);

            // 获取根节点
            Element root = domDoc.getDocumentElement();

            Element raw = (Element) root.getElementsByTagName("rawtext").item(0);
            buffer.append("【识别结果】").append(raw.getFirstChild().getNodeValue());
            buffer.append("\n");

            Element e = (Element) root.getElementsByTagName("result").item(0);

            Element focus = (Element) e.getElementsByTagName("focus").item(0);
            buffer.append("【FOCUS】").append(focus.getFirstChild().getNodeValue());
            buffer.append("\n");

            Element action = (Element) e.getElementsByTagName("action").item(0);
            Element operation = (Element) action.getElementsByTagName("operation").item(0);
            buffer.append("【ACTION】").append(operation.getFirstChild().getNodeValue());
            buffer.append("\n");


        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer.append("【ALL】").append(xml);
        return buffer.toString();
    }
}
