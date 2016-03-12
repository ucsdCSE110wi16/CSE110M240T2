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
 * Created by devinhickey on 2/8/16.
 */
public class QuestionList extends Activity {

    Intent thisIntent;
    Context ctx;
    User myUser;

    TextView myProgress;
    String lessonTitle;
    String chapterTitle;
    int numQuestionsCorrect;
    int chapterNum;
    int numQuestions;
    int lessonNum;
    int qNum;

    ArrayList<Button> myButtons = new ArrayList<Button>();

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.question_list);

        thisIntent = this.getIntent();
        ctx = this;

        System.out.println("Inside QList OnCreate");

        myUser = new User();
        numQuestionsCorrect = 0;

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
        TextView titleBar = (TextView) abLayout.findViewById(R.id.actionBarTitle);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hooome");
                Intent newIntent = new Intent(QuestionList.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);
            }
        });

        // end ActionBar

        // Dynamically create buttons
        lessonTitle = this.getIntent().getExtras().getString("LessonTitle");
        numQuestions = this.getIntent().getExtras().getInt("NumQuestions");
        chapterNum = this.getIntent().getExtras().getInt("ChapterNum");
        lessonNum = this.getIntent().getExtras().getInt("LessonNum");
        chapterTitle = this.getIntent().getExtras().getString("ChapterTitle");

        titleBar.setText(chapterTitle);

        System.out.println("About to create buttons....");
        for (int i = 0; i < numQuestions; i++) {

            Button newButton = new Button(this);
            myButtons.add(newButton);
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.questionLayout);
            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layParam.setMargins(20, 20, 20, 20);
            myLayout.addView(newButton, layParam);

            System.out.println("After layout");

            newButton.setText("Question " + (i + 1));
            newButton.setTextColor(getResources().getColor(R.color.white));
            newButton.setBackgroundColor(getResources().getColor(R.color.peter));

            System.out.println("After color set");

            qNum = i+1;

            newButton.setOnClickListener(new View.OnClickListener() {
                int currQuestion = qNum;
                @Override
                public void onClick(View v) {

                    QuestionXML qXML = new QuestionXML(chapterNum, lessonNum, lessonTitle, currQuestion, ctx);

                    ArrayList<String> responses = qXML.getResponses();
                    ArrayList<String> answers = qXML.getAnswers();
                    String questionType = qXML.getQuestionType();

                    Intent newIntent;

                    if (questionType.equals("MCQ")) {

                       newIntent = new Intent(QuestionList.this, MCQuestion.class);
                        newIntent.putExtra("Responses", responses);

                    } else if (questionType.equals("Text")) {

                        newIntent = new Intent(QuestionList.this, TextQuestion.class);

                    } else if (questionType.equals("Check")){

                        newIntent = new Intent(QuestionList.this, CheckQuestion.class);
                        newIntent.putExtra("Responses", responses);

                    } else {
                        return;

                    }

                    newIntent.putExtra("ChapterNum", chapterNum);
                    newIntent.putExtra("ChapterTitle", chapterTitle);
                    newIntent.putExtra("LessonNum", lessonNum);
                    newIntent.putExtra("LessonTitle", lessonTitle);
                    newIntent.putExtra("QuestionNum", currQuestion);
                    newIntent.putExtra("Answers", answers);


                    startActivity(newIntent);



                }
            });

        }

        int numDone = checkDone();
        String textToShow = ""+ numDone + "/" + numQuestions;
        myProgress.setText(textToShow);

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Onresume in QList");
        System.out.println(User.data);
        int numDone = checkDone();

        String textToShow = ""+ numDone + "/" + numQuestions;
        myProgress.setText(textToShow);

    }

    /*
        Set the button colors based on correct or incorrect
        Returns the total number of correct questions

     */
    public int checkDone() {

        System.out.print("Inside CheckDone");
        int numQuestions = 0;
        for (int i = 0; i < myButtons.size(); i++) {
            Button thisButton = myButtons.get(i);
            int index = i + 1;
            int check = myUser.retrieveQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(index));

            System.out.println("After getting check");

            if (check == -1) {
                System.out.println("Set Red");
                thisButton.setBackgroundColor(getResources().getColor(R.color.red));
            } else if (check == 1) {
                System.out.println("Set Green");
                thisButton.setBackgroundColor(getResources().getColor(R.color.green));
                numQuestions++;

            } else {
                System.out.print("Default color");
                thisButton.setBackgroundColor(getResources().getColor(R.color.peter));

            }

        }
        if (numQuestions == this.numQuestions) {
            System.out.println("Q == Q");
            myUser.updateLesson(Integer.toString(chapterNum), Integer.toString(lessonNum), true);

        }
        return numQuestions;

    } // end CheckDone


}
