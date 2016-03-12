package com.project.cse110.geometryapp;

/**
 * Created by devinhickey on 3/11/16.
 */

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.test.suitebuilder.annotation.LargeTest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
public class TestWrongQuestion {

    private int chapterNum = 4;
    private int lessonNum = 1;
    private int qNum = 3;
    private ArrayList<String> answers;
    private String chapterTitle = "Circles";

    @Rule
    public ActivityTestRule<TextQuestion> mActivityRule = new ActivityTestRule<>(
            TextQuestion.class, true, false);


    @Before
    public void intentPrep() {

        System.out.println("Inside Intent Prep");

        answers = new ArrayList<>();
        answers.add("3");

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
        onView(withId(R.id.answerText)).perform(typeText("0"), closeSoftKeyboard());
        onView(withId(R.id.textSubmit)).perform(click());
        onView(withText("Incorrect")).check(matches(isDisplayed()));

    }

}
