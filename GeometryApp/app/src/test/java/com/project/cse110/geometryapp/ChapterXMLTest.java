package com.project.cse110.geometryapp;

import android.content.Context;
import android.test.ServiceTestCase;

import com.project.cse110.geometryapp.ChapterXML;

import org.junit.*;

import java.lang.reflect.Method;
import java.util.*;

import java.lang.String;

import dalvik.annotation.TestTarget;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

/**
 * Created by Abhishek on 3/6/16.
 */
public class ChapterXMLTest {

    private ChapterXML chapterXML;
    private String title;
    private int chapterNumber;
    private int numLessons;
    private ArrayList<String> lessonNames;

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
    public void testChapterOne() {
        chapterXML = new ChapterXML(1, getTestContext());
        title = chapterXML.getTitle();
        chapterNumber = chapterXML.getChapterNumber();
        numLessons = chapterXML.getNumLessons();
        lessonNames = chapterXML.getLessonNames();
        assertEquals("Tools of Geometry", title);
        assertEquals(1, chapterNumber);
        assertEquals(2, numLessons);
        assertEquals("Lines, Line Segments, Rays", lessonNames.get(0));
        assertEquals("Points, Lines, Planes", lessonNames.get(1));
    }

    @Test
    public void testChapterTwo() {
        chapterXML = new ChapterXML(2, getTestContext());
        title = chapterXML.getTitle();
        chapterNumber = chapterXML.getChapterNumber();
        numLessons = chapterXML.getNumLessons();
        lessonNames = chapterXML.getLessonNames();
        assertEquals("Angles and Lines", title);
        assertEquals(2, chapterNumber);
        assertEquals(3, numLessons);
        assertEquals("Angles", lessonNames.get(0));
        assertEquals("Intersecting Lines", lessonNames.get(1));
        assertEquals("Parallel Lines", lessonNames.get(2));
    }

}