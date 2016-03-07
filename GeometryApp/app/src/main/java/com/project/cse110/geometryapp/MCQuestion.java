package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devinhickey on 1/31/16.
 */
public class MCQuestion extends Activity {

    Intent thisIntent;
    Context ctx;
    Preferences myPreferences;
    User myUser;

    AlertDialog answerAlert;
    AlertDialog.Builder dialogBuilder;

    // Global variables for extras passed in intent
    int chapterNum;
    int correctAnswer;
    int buttonNum;
    int lessonNum;
    int qNum;
    String chapterTitle;
    ArrayList<String> responses;
    ArrayList<String> answers;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.mcq_question);

        thisIntent = this.getIntent();
        ctx = this;
//        myPreferences = new Preferences(this);
//        myUser = myPreferences.retrieveUserInfo();


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

        myProgress.setVisibility(View.INVISIBLE);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Home");
                Intent newIntent = new Intent(MCQuestion.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);
            }
        });

        // end ActionBar


        // Create the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);

        // Set the picture of the question
        ImageView image = (ImageView) findViewById(R.id.imageView);

        // Get the extras
        chapterNum = getIntent().getExtras().getInt("ChapterNum");
        lessonNum = getIntent().getExtras().getInt("LessonNum");
        qNum = getIntent().getExtras().getInt("QuestionNum");
        responses = getIntent().getExtras().getStringArrayList("Responses");
        answers = getIntent().getExtras().getStringArrayList("Answers");
        chapterTitle = getIntent().getExtras().getString("ChapterTitle");

        titleBar.setText(chapterTitle);

        // Set the image
        Drawables draw = new Drawables(chapterNum, lessonNum);
        int imageDrawable = draw.getQuestionImage(qNum);
        image.setImageResource(imageDrawable);

        //getQuestionImage(image);

        // Create MC buttons
        for (int i = 0; i < responses.size(); i++) {
            Button newButton = new Button(this);

            newButton.setTextColor(getResources().getColor(R.color.white));
            newButton.setBackgroundColor(getResources().getColor(R.color.peter));

            LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearButtonLayout);
            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layParam.setMargins(10, 10, 10, 10);

            // Add the buttons with the layout params
            myLayout.addView(newButton, layParam);

            newButton.setText(responses.get(i));

            correctAnswer = Integer.parseInt(answers.get(0));
            buttonNum = i+1;

            newButton.setOnClickListener(new View.OnClickListener() {
                int myButtonNum = buttonNum;
                @Override
                public void onClick(View v) {

                    if (myButtonNum == correctAnswer) {
                        System.out.println("Correct Answer");
                        dialogBuilder.setMessage("Correct!");
                        myUser.updateQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum), true);
                    } else {
                        System.out.println("Incorrect Answer");
                        dialogBuilder.setMessage("Incorrect!");
                        myUser.updateQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum), false);
                    }

                    dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            System.out.println("Inside OK");

                            finish();
                        }
                    });
                    answerAlert = dialogBuilder.create();
                    answerAlert.show();
                }
            });

        }

    }


    @Override
    public void onStop() {
        super.onStop();
        finish();

    }


    /*
        Called to set the image of the question.
        Param: the image view to set the png for.

     */
//    public void getQuestionImage(ImageView image) {
//        System.out.println("Getting Question Image");
//
//        switch (chapterNum) {
//
//            case 1:
//                switch (lessonNum) {
//                    case 1:
//                        switch (qNum) {
//
//                            case 1:
//                                image.setImageResource(R.drawable.c1_l1_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c1_l1_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c1_l1_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c1_l1_q4);
//                                break;
//                            case 5:
//                                image.setImageResource(R.drawable.c1_l1_q5);
//                                break;
//                            case 6:
//                                image.setImageResource(R.drawable.c1_l1_q6);
//                                break;
//                            case 7:
//                                image.setImageResource(R.drawable.c1_l1_q7);
//                                break;
//                            default:
//                                break;
//
//
//                        }
//
//                        break; // end Lesson 1
//
//                    case 2:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c1_l2_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c1_l2_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c1_l2_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c1_l2_q4);
//                                break;
//                            case 5:
//                                image.setImageResource(R.drawable.c1_l2_q5);
//                                break;
//                            default:
//                                break;
//
//
//                        }
//                        break;
//
//                    default:
//                        break; // end Chapter 1 Lesson 2
//
//
//                }
//                break; // end Chapter 1
//
//            case 2:
//                switch (lessonNum) {
//                    case 1:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c2_l1_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c2_l1_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c2_l1_q3);
//                                break;
//
//                            default:
//                                break;
//
//
//                        }
//
//                        break; // end Chapter 2 Lesson 1
//
//                    case 2:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c2_l2_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c2_l2_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c2_l2_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c2_l2_q4);
//                                break;
//                            case 5:
//                                image.setImageResource(R.drawable.c2_l2_q5);
//                                break;
//                            case 6:
//                                image.setImageResource(R.drawable.c2_l2_q6);
//                                break;
//                            case 7:
//                                image.setImageResource(R.drawable.c2_l2_q7);
//                                break;
//
//                            default:
//                                break; // end Chapter 2 Lesson 2
//
//
//                        }
//                        break;
//
//                    case 3:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c2_l3_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c2_l3_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c2_l3_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c2_l3_q4);
//                                break;
//
//                            default:
//                                break;
//
//                        }
//                        break;
//
//                    default:
//                        break; // end Chapter 2 Lesson 3
//
//                }
//                break;  // end Chapter 2
//
//            case 3:
//                switch (lessonNum) {
//                    case 1:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c3_l1_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c3_l1_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c3_l1_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c3_l1_q4);
//                                break;
//                            case 5:
//                                image.setImageResource(R.drawable.c3_l1_q5);
//                                break;
//
//                            default:
//                                break;
//
//
//                        }
//
//                        break; // end Chapter 3 Lesson 1
//
//                    case 2:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c3_l2_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c3_l2_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c3_l2_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c3_l2_q4);
//                                break;
//                            case 5:
//                                image.setImageResource(R.drawable.c3_l2_q5);
//                                break;
//
//                            default:
//                                break;
//
//
//                        }
//                        break;
//
//                    default:
//                        break; // end Chapter 3 Lesson 2
//
//                }
//                break;
//
//            case 4:
//                switch (lessonNum) {
//                    case 1:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c4_l1_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c4_l1_q2);
//                                break;
//
//                            default:
//                                break;
//
//
//                        }
//
//                        break; // end Chapter 4 Lesson 1
//
//                    case 2:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c4_l2_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c4_l2_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c4_l2_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c4_l2_q4);
//                                break;
//
//                            default:
//                                break;
//
//
//                        }
//                        break;  // end Chapter 4 Lesson 2
//
//                    case 3:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c4_l3_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c4_l3_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c4_l3_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c4_l3_q4);
//                                break;
//                            case 5:
//                                image.setImageResource(R.drawable.c4_l3_q5);
//                                break;
//
//                            default:
//                                break;
//
//                        }
//
//                        break;  // end Chapter 4 Lesson 3
//
//                    default:
//                        break;
//
//                }
//                break;  // end Chapter 4
//
//            case 5:
//                switch (lessonNum) {
//                    case 1:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c5_l1_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c5_l1_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c5_l1_q3);
//                                break;
//
//                            default:
//                                break;
//
//
//                        }
//
//                        break;  // end Chapter 5 Lesson 1
//
//                    case 2:
//                        switch (qNum) {
//                            case 1:
//                                image.setImageResource(R.drawable.c5_l2_q1);
//                                break;
//                            case 2:
//                                image.setImageResource(R.drawable.c5_l2_q2);
//                                break;
//                            case 3:
//                                image.setImageResource(R.drawable.c5_l2_q3);
//                                break;
//                            case 4:
//                                image.setImageResource(R.drawable.c5_l2_q4);
//                                break;
//                            case 5:
//                                image.setImageResource(R.drawable.c5_l2_q5);
//                                break;
//
//                            default:
//                                break;
//
//
//                        }
//                        break;  // end Chapter 5 Lesson 2
//
//                    default:
//                        break;
//
//                }
//                break;
//
//            default:
//
//                break;
//
//
//        }
//
//
//    }

    /*

        Set the buttons to green or red based on correct or incorrect answers

     */
//    public void setStaticArray(int value, boolean correct) {
//        switch (chapterNum) {
//
//            case 1:
//                switch (lessonNum) {
//                    case 1:
//                        QuestionList.c1l1[qNum-1] = value;
//
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC1L1++;
//                        }
//
//                        break; // end Lesson 1
//
//                    case 2:
//                        QuestionList.c1l2[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC1L2++;
//                        }
//                        break;
//
//                    default:
//                        break; // end Chapter 1 Lesson 2
//
//
//                }
//                break; // end Chapter 1
//
//            case 2:
//                switch (lessonNum) {
//                    case 1:
//                        QuestionList.c2l1[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC2L1++;
//                        }
//                        break; // end Chapter 2 Lesson 1
//
//                    case 2:
//                        QuestionList.c2l2[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC2L2++;
//                        }
//                        break;  // end Chapter 2 Lesson 2
//
//                    case 3:
//                        QuestionList.c2l3[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC2L3++;
//                        }
//                        break;
//
//                    default:
//                        break; // end Chapter 2 Lesson 3
//
//                }
//                break;  // end Chapter 2
//
//            case 3:
//                switch (lessonNum) {
//                    case 1:
//                        QuestionList.c3l1[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC3L1++;
//                        }
//                        break; // end Chapter 3 Lesson 1
//
//                    case 2:
//                        QuestionList.c3l2[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC3L2++;
//                        }
//                        break;
//
//                    default:
//                        break; // end Chapter 3 Lesson 2
//
//                }
//                break;
//
//            case 4:
//                switch (lessonNum) {
//                    case 1:
//                        QuestionList.c4l1[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC4L1++;
//                        }
//                        break; // end Chapter 4 Lesson 1
//
//                    case 2:
//                        QuestionList.c4l2[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC4L2++;
//                        }
//                        break;  // end Chapter 4 Lesson 2
//
//                    case 3:
//                        QuestionList.c4l3[qNum-1] = value;
//                        if (correct) {
//                            QuestionList.numQuestionsCorrectC4L3++;
//                        }
//                        break;  // end Chapter 4 Lesson 3
//
//                    default:
//                        break;
//
//                }
//                break;  // end Chapter 4

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

//            default:
//
//                break;
//
//
//        }
//
//    }

}
