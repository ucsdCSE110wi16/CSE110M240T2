package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by devinhickey on 1/29/16.
 */

public class Main extends Activity {

    Context ctx;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Firebase.setAndroidContext(this);
        System.out.println("Inside OnCreate main");


        setContentView(R.layout.content_main);

        ImageButton first = (ImageButton) findViewById(R.id.topic1);
        ImageButton second = (ImageButton) findViewById(R.id.topic2);
        ImageButton third = (ImageButton) findViewById(R.id.topic3);
        ImageButton fourth = (ImageButton) findViewById(R.id.topic4);
        ImageButton fifth = (ImageButton) findViewById(R.id.topic5);

        ctx = this;

        // Start ActionBar
        ActionBar ab = getActionBar();

        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflater = LayoutInflater.from(this);

        LinearLayout abLayout = (LinearLayout) inflater.inflate(R.layout.titlebarlayout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.LEFT);

        ab.setCustomView(abLayout, params);
        ab.setDisplayHomeAsUpEnabled(false);


        // Make the buttons on the ab disappear
        TextView progress = (TextView) abLayout.findViewById(R.id.progress);
        Button homeButton = (Button) abLayout.findViewById(R.id.home);

        progress.setVisibility(View.INVISIBLE);
        homeButton.setText("Logout");
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                Preferences user_info = new Preferences(getApplicationContext());
                user_info.clearUserInfo();
                Intent newIntent = new Intent(Main.this, LoginScreen.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                finish();
            }
        });

        // end ActionBar

        first.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent topic1Intent = new Intent(Main.this, LessonList.class);


                        ChapterXML chap = new ChapterXML(1, ctx);
                        String title = chap.getTitle();
                        int numLessons = chap.getNumLessons();
                        int chapNum = chap.getChapterNumber();
                        ArrayList<String> lessonTitles = chap.getLessonNames();

                        topic1Intent.putExtra("ChapterNum", chapNum);
                        topic1Intent.putExtra("ChapterTitle", title);
                        topic1Intent.putExtra("NumLessons", numLessons);
                        topic1Intent.putExtra("LessonTitles", lessonTitles);
                        System.out.println("Chapter 1 clicked: Title: " + title + " Num Lessons: " + numLessons);
                        startActivity(topic1Intent);
                    }
                }
        );

        second.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent topic2Intent = new Intent(Main.this, LessonList.class);

                        ChapterXML chap = new ChapterXML(2, ctx);
                        String title = chap.getTitle();
                        int numLessons = chap.getNumLessons();
                        int chapNum = chap.getChapterNumber();
                        ArrayList<String> lessonTitles = chap.getLessonNames();

                        topic2Intent.putExtra("LessonTitles", lessonTitles);
                        topic2Intent.putExtra("ChapterNum", chapNum);
                        topic2Intent.putExtra("ChapterTitle", title);
                        topic2Intent.putExtra("NumLessons", numLessons);
                        System.out.println("Chapter 1 clicked: Title: " + title + " Num Lessons: " + numLessons);

                        startActivity(topic2Intent);

                    }
                }
        );


        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Third Button Clicked");

                Intent topic3Intent = new Intent(Main.this, LessonList.class);

                ChapterXML chap = new ChapterXML(3, ctx);
                String title = chap.getTitle();
                int numLessons = chap.getNumLessons();
                int chapNum = chap.getChapterNumber();
                ArrayList<String> lessonTitles = chap.getLessonNames();

                topic3Intent.putExtra("LessonTitles", lessonTitles);
                topic3Intent.putExtra("ChapterNum", chapNum);
                topic3Intent.putExtra("ChapterTitle", title);
                topic3Intent.putExtra("NumLessons", numLessons);
                System.out.println("Chapter 1 clicked: Title: " + title + " Num Lessons: " + numLessons);
                startActivity(topic3Intent);

            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Inside 4th button onClick");

                Intent topic4Intent = new Intent(Main.this, LessonList.class);

                ChapterXML chap = new ChapterXML(4, ctx);
                String title = chap.getTitle();
                int numLessons = chap.getNumLessons();
                int chapNum = chap.getChapterNumber();

                ArrayList<String> lessonTitles = chap.getLessonNames();

                topic4Intent.putExtra("LessonTitles", lessonTitles);
                topic4Intent.putExtra("ChapterNum", chapNum);
                topic4Intent.putExtra("ChapterTitle", title);
                topic4Intent.putExtra("NumLessons", numLessons);
                System.out.println("Chapter 1 clicked: Title: " + title + " Num Lessons: " + numLessons);
                startActivity(topic4Intent);

            }
        });

        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Inside 5th button click");

                Intent topic5Intent = new Intent(Main.this, LessonList.class);

                ChapterXML chap = new ChapterXML(5, ctx);
                String title = chap.getTitle();
                int numLessons = chap.getNumLessons();
                int chapNum = chap.getChapterNumber();
                ArrayList<String> lessonTitles = chap.getLessonNames();

                topic5Intent.putExtra("LessonTitles", lessonTitles);
                topic5Intent.putExtra("ChapterNum", chapNum);
                topic5Intent.putExtra("ChapterTitle", title);
                topic5Intent.putExtra("NumLessons", numLessons);
                System.out.println("Chapter 1 clicked: Title: " + title + " Num Lessons: " + numLessons);
                startActivity(topic5Intent);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Inside onResume");

    }
}
