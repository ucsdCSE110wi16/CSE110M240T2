package com.project.cse110.geometryapp;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Marchness on 3/9/16.
 */
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.test.suitebuilder.annotation.LargeTest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;

import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

//Test 1: Login
@LargeTest
public class TestLogin {

    private String usernameToType;
    private String passwordToType;

    @Rule
    public ActivityTestRule<LoginScreen> mActivityRule = new ActivityTestRule<>(
            LoginScreen.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        usernameToType = "t@t.com";
        passwordToType = "test";
        System.out.println("Hello I am initValid***************");

    }

    @Test
    public void testUser() {
        System.out.println("TESTING USER LOGIN..........");
        Activity currActivity = mActivityRule.getActivity();

        // Type text and then press the button.
        onView(withId(R.id.etUsername)).perform(typeText(usernameToType), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(passwordToType), closeSoftKeyboard());

        // Check that the text was changed
        onView(withId(R.id.etUsername)).check(matches(withText(usernameToType)));
        onView(withId(R.id.bLogin)).perform(click());

        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (Exception e) {

        }

        Activity afterActivity = mActivityRule.getActivity();

        System.out.println(currActivity.toString());
        System.out.println(afterActivity.toString());

        //onView(withId(R.id.registerButton)).perform(click());

    }
/*
    @Test
    public void testTest() {
        System.out.println("Hello I am in testtest***************");
       // onView(withId(R.id.etUsername)).perform(typeText(usernameToType), closeSoftKeyboard());
       // onView(withId(R.id.etUsername)).check(matches(withText(usernameToType)));

    }
    */
}

//Test 2: