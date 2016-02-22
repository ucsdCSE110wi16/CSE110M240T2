package com.project.cse110.geometryapp;

import android.content.Context;

import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Abhishek on 2/20/16.
 */
public class ChapterXML {

    private String title;
    private int chapterNumber;
    private int numLessons;
    private ArrayList<String> lessonNames;

    public ChapterXML (int number, Context context) {

        this.chapterNumber = number;
        DocParser builder = new DocParser(context);

        String location = "/chapters/chapter" + number;
        Node node = builder.getNode(location);
        this.title = node.getAttributes().getNamedItem("title").getNodeValue();

        location = "/chapters/chapter" + number + "/lesson";
        NodeList list = builder.getNodeList(location);
        this.numLessons = list.getLength();

        this.lessonNames = new ArrayList<String>();
        for (int i = 0; i < numLessons; i++) {
            this.lessonNames.add(list.item(i).getAttributes().getNamedItem("title").getNodeValue());
        }

        builder.closeStream();
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

    public ArrayList<String> getLessonNames() {

        return lessonNames;
    }
}
