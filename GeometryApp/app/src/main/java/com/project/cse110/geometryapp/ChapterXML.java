package com.project.cse110.geometryapp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

import java.io.IOException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.InputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Abhishek on 2/20/16.
 */
public class ChapterXML {

    String title;
    int chapterNumber;
    int numLessons;

    public ChapterXML (int number, InputStream in) {

        this.chapterNumber = number;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = builder.parse(in);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XPath xPath =  XPathFactory.newInstance().newXPath();
        String location = "/chapters/chapter" + number;
        Node node = null;
        try {
            node = (Node) xPath.compile(location).evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        this.title = node.getAttributes().getNamedItem("title").getNodeValue();

        location = "/chapters/chapter" + number + "/lesson";
        NodeList list = null;
        try {
            list = (NodeList) xPath.compile(location).evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        this.numLessons = list.getLength();

    }

    public String getTitle() {
        return this.title;
    }

    public int getNumLessons() {
        return this.numLessons;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }
}
