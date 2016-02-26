package com.project.cse110.geometryapp;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Abhishek on 2/20/16.
 */
public class LessonXML {
    private String title;
    private ArrayList<String> body;
    private String bodyString;
    private int chapterNumber;
    private int lessonNumber;
    private int numQuestions;

    public LessonXML(int chapNum, int lessonNum, Context context) {
        this.chapterNumber = chapNum;
        this.lessonNumber = lessonNum;

        DocParser builder = new DocParser(context);

        String location = "/chapters/chapter" + chapNum + "/lesson";
        NodeList list = builder.getNodeList(location);
        Node currLesson = list.item(lessonNum-1);
        this.title = currLesson.getAttributes().getNamedItem("title").getNodeValue();
        this.bodyString = currLesson.getFirstChild().getNextSibling().getTextContent();
        this.body = new ArrayList<String>(Arrays.asList(bodyString.split("##")));

        location = "/chapters/chapter" + chapNum + "/lesson[@title='" + this.title + "']/test/question";
        list = builder.getNodeList(location);
        this.numQuestions = list.getLength();
        builder.closeStream();
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getBody() {
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
