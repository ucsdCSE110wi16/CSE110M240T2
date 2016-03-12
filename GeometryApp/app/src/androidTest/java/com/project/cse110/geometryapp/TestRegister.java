package com.project.cse110.geometryapp;

/**
 * Created by Kedar on 3/11/16.
 */

import java.util.Random;
import java.lang.String;
import java.lang.*;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.test.suitebuilder.annotation.LargeTest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;

import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

//Test 2: Register
@LargeTest
public class TestRegister {

    public String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }


    private String email;
    private String password;
    private String confirmPassword;

    @Rule
    public ActivityTestRule<RegisterScreen> mActivityRule = new ActivityTestRule<>(
            RegisterScreen.class);

    @Before
    public void initValidString() {
        // Specify a valid string.

        Random rng = new Random();

        int i = rng.nextInt() % 1000;
        email = "ice" + i + "@gmail.com";

        System.out.println(email);
        password = "123456";
        confirmPassword = "123456";

    }

    @Test
    public void testUser() {
        System.out.println("TESTING USER REGISTER..........");
        // Type text and then press the button.
        onView(withId(R.id.regUsername)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.regPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(confirmPassword), closeSoftKeyboard());

        // Check that the text was changed
        onView(withId(R.id.regUsername)).check(matches(withText(email)));
        onView(withId(R.id.bRegister)).perform(click());
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (Exception e) {

        }

        onView(withText("Account successfully created!")).check(matches(isDisplayed()));

        //onView(withId(R.id.registerButton)).perform(click());

    }

}
