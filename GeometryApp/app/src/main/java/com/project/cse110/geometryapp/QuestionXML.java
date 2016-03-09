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
public class QuestionXML {
    private int questionNumber;
    private String questionType;
    private String imageName;
    private String responsesString;
    private String answersString;
    private ArrayList<String> responses;
    private ArrayList<String> answers;
    private int chapterNumber;
    private int lessonNumber;
    private String lessonTitle;

    public QuestionXML(int chapterNumber, int lessonNumber, String lessonTitle, int questionNumber, Context context) {
        this.chapterNumber = chapterNumber;
        this.lessonNumber = lessonNumber;
        this.lessonTitle = lessonTitle;
        this.questionNumber = questionNumber;

        DocParser builder = new DocParser(context);
        String location = "/chapters/chapter" + chapterNumber + "/lesson[@title='" + lessonTitle + "']/test/question";
        NodeList list = builder.getNodeList(location);
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

        this.responses = new ArrayList<String>(Arrays.asList(responsesString.split("##")));
        this.answers = new ArrayList<String>(Arrays.asList(answersString.split("##")));
        builder.closeStream();
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
