package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devinhickey on 1/29/16.
 */
public class LessonList extends Activity {

    Intent thisIntent;
    Context ctx;
    int lessonNum;
    int currLesson;
    int chapterNum;
    ArrayList<String> lessonTitles;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        System.out.println("Inside LessonList onCreate");
        setContentView(R.layout.lesson_list);

        thisIntent = this.getIntent();
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
        Button logoutButton = (Button) abLayout.findViewById(R.id.logout);
        Button homeButton = (Button) abLayout.findViewById(R.id.home);
        TextView titleText = (TextView) abLayout.findViewById(R.id.actionBarTitle);

        // Set the title to the chapter title
        String myTitle = this.getIntent().getExtras().getString("ChapterTitle");
        titleText.setText(myTitle);

        logoutButton.setVisibility(View.INVISIBLE);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Home Button Clicked");
                Intent newIntent = new Intent(LessonList.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);

            }
        });

        // end ActionBar


        lessonNum = this.getIntent().getExtras().getInt("NumLessons");
        chapterNum = this.getIntent().getExtras().getInt("ChapterNum");
        lessonTitles = this.getIntent().getExtras().getStringArrayList("LessonTitles");

        for (int i = 0; i < lessonNum; i++) {
            final Button newButton = new Button(this);
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.lessonList);
            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            currLesson = i+1;

            // Add the buttons with the layout params
            myLayout.addView(newButton, layParam);
            newButton.setText(lessonTitles.get(i));
            newButton.setOnClickListener(new View.OnClickListener() {
                int lesson = currLesson;
                @Override
                public void onClick(View v) {
                    System.out.println("Lesson clicked: "+ newButton.getText());
                    Intent newIntent = new Intent(LessonList.this, LessonDescription.class);

                    System.out.println("Lesson Number in onClick: " + lesson);
                    LessonXML lessonXML = new LessonXML(chapterNum, lesson, ctx);

                    String lessonDescription = lessonXML.getBody();
                    String lessonTitle = lessonXML.getTitle();
                    int lessonNum = lessonXML.getLessonNumber();
                    int numQuestions = lessonXML.getNumQuestions();

                    System.out.println("Body: " + lessonDescription);
                    System.out.println("Title: " + lessonTitle);
                    System.out.println("LessonNum: " + lessonNum);
                    System.out.println("NumQuestions: " + numQuestions);

                    newIntent.putExtra("LessonDescription", lessonDescription);
                    newIntent.putExtra("LessonTitle", lessonTitle);
                    newIntent.putExtra("ChapterNum", chapterNum);
                    newIntent.putExtra("LessonNum", lessonNum);
                    newIntent.putExtra("NumQuestions", numQuestions);

                    startActivity(newIntent);

                }
            });

        }

    }
}
