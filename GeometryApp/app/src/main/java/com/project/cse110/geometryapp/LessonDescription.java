package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devinhickey on 2/6/16.
 */
public class LessonDescription extends FragmentActivity {

    Intent thisIntent;
    Context ctx;
    String chapterTitle;
    String lessonTitle;
    int currLesson;
    int chapterNum;
    int numQuestions;

    LessonFragment frag;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        System.out.println("Inside LessonDescription onCreate");
        setContentView(R.layout.lesson_description);

        //frag = (LessonFragment) getFragmentManager().findFragmentById(R.id.fragment);
        System.out.print("After setContent in OnCreate LD");

        thisIntent = this.getIntent();
        ctx = this;

        // Get extras
        //ArrayList<String> description = getIntent().getExtras().getStringArrayList("LessonDescription");
        chapterNum = getIntent().getExtras().getInt("ChapterNum");
        lessonTitle = getIntent().getExtras().getString("LessonTitle");
        currLesson = getIntent().getExtras().getInt("LessonNum");
        numQuestions = getIntent().getExtras().getInt("NumQuestions");
        chapterTitle = getIntent().getExtras().getString("ChapterTitle");
        // End get extras

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
        TextView myProgress = (TextView) abLayout.findViewById(R.id.progress);
        Button homeButton = (Button) abLayout.findViewById(R.id.home);
        TextView titleBar = (TextView) abLayout.findViewById(R.id.actionBarTitle);

        titleBar.setText(chapterTitle);

        myProgress.setVisibility(View.INVISIBLE);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Home Button");
                Intent newIntent = new Intent(LessonDescription.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);
            }
        });

        // end ActionBar


        //TextView text = (TextView) findViewById(R.id.lessonDescription);

        //text.setText(description);

        Button skipButton = (Button) findViewById(R.id.next);
        skipButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent questionIntent = new Intent(LessonDescription.this, QuestionList.class);

                questionIntent.putExtra("ChapterNum", chapterNum);
                questionIntent.putExtra("ChapterTitle", chapterTitle);
                questionIntent.putExtra("LessonTitle", lessonTitle);
                questionIntent.putExtra("LessonNum", currLesson);
                questionIntent.putExtra("NumQuestions", numQuestions);

                startActivity(questionIntent);

            }

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();

    }
}
