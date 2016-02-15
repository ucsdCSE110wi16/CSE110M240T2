package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by devinhickey on 2/8/16.
 */
public class QuestionList extends Activity {

    Intent thisIntent;
    int lessonNum;

    static int l1q1 = 0;
    static int l1q2 = 0;
    static int l1q3 = 0;

    static int l2q1 = 0;
    static int l2q2 = 0;
    static int l2q3 = 0;


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.question_list);

        thisIntent = this.getIntent();
        lessonNum = thisIntent.getExtras().getInt("Lesson");

        System.out.println("Lesson Num Q List: " + lessonNum);

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

        logoutButton.setVisibility(View.INVISIBLE);
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
        Button firstButton = new Button(this);
        firstButton.setText("Question 1");

        Button secButton = new Button(this);
        secButton.setText("Question 2");

        Button thirdButton = new Button(this);
        thirdButton.setText("Question 3");

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.questionLayout);
        LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Add the buttons with the layout params
        myLayout.addView(firstButton, layParam);
        myLayout.addView(secButton, layParam);
        myLayout.addView(thirdButton, layParam);

//        if (lessonNum == 1) {
//            System.out.println("Inside 1");
//            if (l1q1 == 1) {
//                System.out.println("Inside 11");
//                firstButton.setTextColor(0x00FF00);
//
//            } else if (l1q1 == 2) {
//                System.out.println("Inside 12");
//                firstButton.setTextColor(0xFF0000);
//
//            } else {
//
//                System.out.println("Inside 13");
//                firstButton.setTextColor(0xFFFFFF);
//
//            }
//
//            if (l1q2 == 1) {
//
//                secButton.setBackgroundColor(0x00FF00);
//
//            } else if (l1q2 == 2) {
//
//                secButton.setBackgroundColor(0xFF0000);
//
//            } else {
//
//                //secButton.setBackgroundColor(0xFFFFFF);
//
//            }
//
//            if (l1q3 == 1) {
//
//                thirdButton.setBackgroundColor(0x00FF00);
//
//            } else if (l1q3 == 2) {
//
//                thirdButton.setBackgroundColor(0xFF0000);
//
//            } else {
//
//
//                //thirdButton.setBackgroundColor(0xFFFFFF);
//            }
//
//
//        } else {
//
//            if (l2q1 == 1) {
//
//                firstButton.setBackgroundColor(0x00FF00);
//
//            } else if (l2q1 == 2) {
//
//
//                firstButton.setBackgroundColor(0xFF0000);
//            } else {
//
//                firstButton.setBackgroundColor(0xFFFFFF);
//
//            }
//
//            if (l2q2 == 1) {
//
//                secButton.setBackgroundColor(0x00FF00);
//
//            } else if (l2q2 == 2) {
//
//                secButton.setBackgroundColor(0xFF0000);
//
//            } else {
//
//                secButton.setBackgroundColor(0xFFFFFF);
//            }
//
//            if (l2q3 == 1) {
//
//                thirdButton.setBackgroundColor(0x00FF00);
//
//            } else if (l2q3 == 2) {
//
//                thirdButton.setBackgroundColor(0xFF0000);
//
//            } else {
//
//
//                thirdButton.setBackgroundColor(0xFFFFFF);
//            }
//
//        }


        // Create the button onClicks
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Inside first button click");

                int png;
                String[] buttons;
                String body;
                String answer;
                Intent newIntent = new Intent(QuestionList.this, LessonQuestion.class);

                if (lessonNum == 1) {

                    buttons = new String[3];
                    buttons[0] = "A";
                    buttons[1] = "B";
                    buttons[2] = "C";

                    answer = "A";

                    png = R.drawable.c1_l1_q1;


                } else {

                    png = R.drawable.c1_l2_q1;
                    body = "Are F,Y,W Coplanar?";
                    newIntent.putExtra("Body", body);

                    buttons = new String[2];
                    buttons[0] = "Yes";
                    buttons[1] = "No";

                    answer = "Yes";

                }

                newIntent.putExtra("Answer", answer);
                newIntent.putExtra("Buttons", buttons);
                newIntent.putExtra("PNG", png);
                newIntent.putExtra("Lesson", lessonNum);

                startActivity(newIntent);

            }
        });

        secButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Inside second Button onClick");

                int png;
                String body;
                String answer;
                String[] buttons;
                Intent newIntent = new Intent(QuestionList.this, LessonQuestion.class);

                if (lessonNum == 1) {
                    png = R.drawable.c1_l1_q2;

                    buttons = new String[3];
                    buttons[0] = "A";
                    buttons[1] = "B";
                    buttons[2] = "C";

                    answer = "A";

                } else {
                    png = R.drawable.c1_l2_q2;

                    body = "Are the points K,W and L collinear?";
                    newIntent.putExtra("Body", body);

                    buttons = new String[2];
                    buttons[0] = "Yes";
                    buttons[1] = "No";

                    answer = "Yes";

                }

                newIntent.putExtra("Answer", answer);
                newIntent.putExtra("Buttons", buttons);
                newIntent.putExtra("PNG", png);
                newIntent.putExtra("Lesson", lessonNum);

                startActivity(newIntent);

            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int png;
                String body;
                String answer;
                String[] buttons;
                Intent newIntent = new Intent(QuestionList.this, LessonQuestion.class);

                if (lessonNum == 1) {

                    png = R.drawable.c1_l1_q6;

                    buttons = new String[3];
                    buttons[0] = "A Line";
                    buttons[1] = "A Ray";
                    buttons[2] = "A Line Segment";

                    answer = "A Ray";

                } else {

                    png = R.drawable.c1_l2_q3;
                    body = "Are the points O and B collinear?";
                    newIntent.putExtra("Body", body);

                    buttons = new String[2];
                    buttons[0] = "Yes";
                    buttons[1] = "No";

                    answer = "Yes";

                }

                newIntent.putExtra("Answer", answer);
                newIntent.putExtra("Buttons", buttons);
                newIntent.putExtra("Lesson", lessonNum);
                newIntent.putExtra("PNG", png);

                startActivity(newIntent);

            }
        });

    }
}
