package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    TextView myProgress;

    int lessonNum;
    int currLesson;
    int chapterNum;
    String chapterTitle;
    ArrayList<String> lessonTitles;
    ArrayList<Button> myButtons = new ArrayList<Button>();

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
        myProgress = (TextView) abLayout.findViewById(R.id.progress);
        Button homeButton = (Button) abLayout.findViewById(R.id.home);
        TextView titleText = (TextView) abLayout.findViewById(R.id.actionBarTitle);

        // Set the title to the chapter title
        chapterTitle = this.getIntent().getExtras().getString("ChapterTitle");
        titleText.setText(chapterTitle);

        // Fill in progress
        checkProgress();

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
            Button newButton = new Button(this);
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.lessonList);
            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            myButtons.add(newButton);

            currLesson = i+1;

            // Add the buttons with the layout params
            myLayout.addView(newButton, layParam);
            newButton.setText(lessonTitles.get(i));
            newButton.setOnClickListener(new View.OnClickListener() {
                int lesson = currLesson;
                @Override
                public void onClick(View v) {

                    Intent newIntent = new Intent(LessonList.this, LessonDescription.class);

                    System.out.println("Lesson Number in onClick: " + lesson);
                    LessonXML lessonXML = new LessonXML(chapterNum, lesson, ctx);

                    ArrayList<String> lessonDescription = lessonXML.getBody();
                    String lessonTitle = lessonXML.getTitle();
                    int lessonNum = lessonXML.getLessonNumber();
                    int numQuestions = lessonXML.getNumQuestions();

                    System.out.println("Body: " + lessonDescription);
                    System.out.println("Title: " + lessonTitle);
                    System.out.println("LessonNum: " + lessonNum);
                    System.out.println("NumQuestions: " + numQuestions);

                    newIntent.putExtra("LessonDescription", lessonDescription); //FIXME: CHANGE LATER TO SEND WHOLE ARRAYLIST
                    newIntent.putExtra("LessonTitle", lessonTitle);
                    newIntent.putExtra("ChapterNum", chapterNum);
                    newIntent.putExtra("ChapterTitle", chapterTitle);
                    newIntent.putExtra("LessonNum", lessonNum);
                    newIntent.putExtra("NumQuestions", numQuestions);

                    startActivity(newIntent);

                }
            });

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        checkProgress();

    }

    public void checkProgress() {

        String myString;
        switch (chapterNum) {

            case 1:

                if (QuestionList.numQuestionsCorrectC1L2 == 4) {

                    myButtons.get(1).setBackgroundColor(Color.GREEN);
                    myString = "1/" + lessonNum;
                    myProgress.setText(myString);

                } else {

                    myButtons.get(1).setBackgroundColor(Color.LTGRAY);
                    myString = "0/" + lessonNum;
                    myProgress.setText(myString);

                }

                break; // end Chapter 1

            case 2:

                myString = "0/" + lessonNum;
                myProgress.setText(myString);

                break;  // end Chapter 2

            case 3:

                myString = "0/" + lessonNum;
                myProgress.setText(myString);

                break;

            case 4:

                myString = "0/" + lessonNum;
                myProgress.setText(myString);

                break;  // end Chapter 4

//            case 5:
//                switch (lessonNum) {
//                    case 1:
//                        QuestionList.c5l1[qNum-1] = value;
//
//                        break;  // end Chapter 5 Lesson 1
//
//                    case 2:
//                        QuestionList.c5l2[qNum-1] = value;
//                        break;  // end Chapter 5 Lesson 2
//
//                    default:
//                        break;
//
//                }
//                break;

            default:

                break;


        }

    }

}
