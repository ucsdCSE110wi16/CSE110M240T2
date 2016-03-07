package com.project.cse110.geometryapp;

import android.content.Context;
import android.test.ServiceTestCase;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Abhishek on 3/6/16.
 */
public class LessonXMLTest {

    private LessonXML lessonXML;
    private String title;
    private ArrayList<String> body;
    private int chapterNumber;
    private int lessonNumber;
    private int numQuestions;

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
    public void tesC1Lesson1() {
        lessonXML = new LessonXML(1, 1, getTestContext());
        title = lessonXML.getTitle();
        body = lessonXML.getBody();
        chapterNumber = lessonXML.getChapterNumber();
        lessonNumber = lessonXML.getLessonNumber();
        numQuestions = lessonXML.getNumQuestions();
        assertEquals("Lines, Line Segments, Rays", title);
        assertEquals(1, chapterNumber);
        assertEquals(1, lessonNumber);
        assertEquals(7, numQuestions);
        assertEquals("Lines: A line is a straight one-dimensional geometric object that extends infinitely in both directions.", body.get(0));
        assertEquals("Line Segment: A line segment is a straight one-dimensional geometric object that has fixed starting and ending points.", body.get(1));
        assertEquals("Ray: A ray is a straight one-dimensional geometric object that has a fixed starting point, but extends infinitely.", body.get(2));
    }

    @Test
    public void testC2Lesson3() {
        lessonXML = new LessonXML(2, 3, getTestContext());
        title = lessonXML.getTitle();
        body = lessonXML.getBody();
        chapterNumber = lessonXML.getChapterNumber();
        lessonNumber = lessonXML.getLessonNumber();
        numQuestions = lessonXML.getNumQuestions();
        assertEquals("Parallel Lines", title);
        assertEquals(2, chapterNumber);
        assertEquals(3, lessonNumber);
        assertEquals(4, numQuestions);
        assertEquals("Parallel Lines: Two lines that never intersect are known as parallel lines.", body.get(0));
        assertEquals("When a line intersects two other lines (which are parallel), the angles formed are identical on either of the parallel lines. For example: If there are two parallel lines, and a third one intersects both of them, a pair of vertical angles formed on the first line will be identical to the corresponding pair of vertical angles formed on the second line.", body.get(1));
    }
}
