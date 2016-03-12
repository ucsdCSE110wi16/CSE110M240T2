package com.project.cse110.geometryapp;

/**
 * Created by devinhickey on 3/11/16.
 */
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.firebase.client.Firebase;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;

import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class TestQuestion {

    private int chapterNum = 2;
    private int lessonNum = 2;
    private int qNum = 1;
    private ArrayList<String> answers;
    private String chapterTitle = "Angles and Lines";

    @Rule
    public ActivityTestRule<TextQuestion> mActivityRule = new ActivityTestRule<>(
            TextQuestion.class, true, false);

    @Before
    public void intentPrep() {

        answers = new ArrayList<>();
        answers.add("156");

        Intent intent = new Intent();
        intent.putExtra("ChapterNum", chapterNum);
        intent.putExtra("LessonNum", lessonNum);
        intent.putExtra("QuestionNum", qNum);
        intent.putExtra("ChapterTitle", chapterTitle);
        intent.putExtra("Answers", answers);

        mActivityRule.launchActivity(intent);

    }

    @Test
    public void testAnswer() {
        System.out.println("Inside Test Answer");
        onView(withId(R.id.answerText)).perform(typeText("156"), closeSoftKeyboard());
        onView(withId(R.id.textSubmit)).perform(click());
        onView(withText("Correct!")).check(matches(isDisplayed()));

    }


}
