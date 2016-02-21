package com.project.cse110.geometryapp;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

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
public class QuestionXML {
    private int questionNumber; //
    private String questionType;
    private String imageName;
    private String responsesString;
    private String answersString;
    private ArrayList<String> responses;
    private ArrayList<String> answers;
    private int chapterNumber; //
    private int lessonNumber; //
    private String lessonTitle; //

    public QuestionXML(int chapterNumber, int lessonNumber, String lessonTitle, int questionNumber, InputStream in) {
        this.chapterNumber = chapterNumber;
        this.lessonNumber = lessonNumber;
        this.lessonTitle = lessonTitle;
        this.questionNumber = questionNumber;

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

        XPath xPath = XPathFactory.newInstance().newXPath();
        String location = "/chapters/chapter" + chapterNumber + "/lesson[@title='" + lessonTitle + "']/test/question";
        NodeList list = null;
        try {
            list = (NodeList) xPath.compile(location).evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Node currQuestion = list.item(questionNumber - 1);
        this.questionNumber = Integer.parseInt(currQuestion.getAttributes().getNamedItem("number").getNodeValue());
        this.questionType = currQuestion.getAttributes().getNamedItem("type").getNodeValue();
        NodeList content = currQuestion.getChildNodes();
        this.imageName = content.item(1).getTextContent();

        if (questionType.equals("Text")) {
            this.responsesString = "";
            this.answersString = content.item(3).getTextContent();
        } else {
            this.responsesString = content.item(3).getTextContent();
            this.answersString = content.item(5).getTextContent();
        }

        //responses = new ArrayList<>(Arrays.asList(responsesString.split("##")));
        //answers = new ArrayList<>(Arrays.asList(answersString.split("##")));
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getImageName() {
        return imageName;
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }
}
