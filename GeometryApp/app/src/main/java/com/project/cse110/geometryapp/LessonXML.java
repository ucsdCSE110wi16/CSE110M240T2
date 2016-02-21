package com.project.cse110.geometryapp;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Abhishek on 2/20/16.
 */
public class LessonXML {
    String title;
    String body;
    int chapterNumber;
    int lessonNumber;
    int numQuestions;

    public LessonXML(int chapNum, int lessonNum, InputStream in) {
        this.chapterNumber = chapNum;
        this.lessonNumber = lessonNum;

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
        String location = "/chapters/chapter" + chapNum + "/lesson";
        NodeList list = null;
        try {
            list = (NodeList) xPath.compile(location).evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        Node currLesson = list.item(lessonNum-1);
        this.title = currLesson.getAttributes().getNamedItem("title").getNodeValue();

        location = "/chapters/chapter" + chapNum + "/lesson[@title='" + this.title + "']/body";
        System.out.println(location);
        Node node = null;
        try {
            node = (Node) xPath.compile(location).evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        this.body = node.getTextContent();

        location = "/chapters/chapter" + chapNum + "/lesson[@title='" + this.title + "']/test/question";
        System.out.println(location);
        list = null;
        try {
            list = (NodeList) xPath.compile(location).evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        this.numQuestions = list.getLength();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public int getNumQuestions() {
        return numQuestions;
    }
}
