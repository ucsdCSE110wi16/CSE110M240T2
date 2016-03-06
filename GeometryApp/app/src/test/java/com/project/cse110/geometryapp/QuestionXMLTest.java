package com.project.cse110.geometryapp;

import android.content.Context;
import android.test.ServiceTestCase;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Abhishek on 3/6/16.
 */
public class QuestionXMLTest {

    private QuestionXML questionXML;
    private int questionNumber;
    private String questionType;
    private String imageName;
    private ArrayList<String> responses;
    private ArrayList<String> answers;
    private int chapterNumber;
    private int lessonNumber;
    private String lessonTitle;

    @Before
    private Context getTestContext() {
        try
        {
            Method getTestContext = ServiceTestCase.class.getMethod("getTestContext");
            return (Context) getTestContext.invoke(this);
        }
        catch (final Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    @Test
    public void testC1L1Question2() {
        questionXML = new QuestionXML(1, 1, "Lines, Line Segments, Rays", 2, getTestContext());
        questionNumber = questionXML.getQuestionNumber();
        questionType = questionXML.getQuestionType();
        imageName = questionXML.getImageName();
        responses = questionXML.getResponses();
        answers = questionXML.getAnswers();
        chapterNumber = questionXML.getChapterNumber();
        lessonNumber = questionXML.getLessonNumber();
        lessonTitle = questionXML.getLessonTitle();

        assertEquals(2, questionNumber);
        assertEquals("MCQ", questionType);
        assertEquals("c1_l1_q2.png", imageName);
        assertEquals("A", responses.get(0));
        assertEquals("B", responses.get(1));
        assertEquals("C", responses.get(2));
        assertEquals(1, answers.get(0));
        assertEquals(1, chapterNumber);
        assertEquals(1, lessonNumber);
        assertEquals("Lines, Line Segments, Rays", lessonTitle);
    }

    @Test
    public void testC3L2Question1() {
        questionXML = new QuestionXML(3, 2, "Quadrilateral Proofs and Angles", 1, getTestContext());
        questionNumber = questionXML.getQuestionNumber();
        questionType = questionXML.getQuestionType();
        imageName = questionXML.getImageName();
        responses = questionXML.getResponses();
        answers = questionXML.getAnswers();
        chapterNumber = questionXML.getChapterNumber();
        lessonNumber = questionXML.getLessonNumber();
        lessonTitle = questionXML.getLessonTitle();

        assertEquals(1, questionNumber);
        assertEquals("Text", questionType);
        assertEquals("c3_l2_q1.png", imageName);
        assertEquals(0, responses.size());
        assertEquals("74", answers.get(0));
        assertEquals(3, chapterNumber);
        assertEquals(2, lessonNumber);
        assertEquals("Quadrilateral Proofs and Angles", lessonTitle);
    }


}
