package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devinhickey on 2/22/16.
 */
public class TextQuestion extends Activity{

    Intent thisIntent;
    Context ctx;

    EditText answerText;
    AlertDialog dialog;
    AlertDialog.Builder dialogBuilder;

    String chapterTitle;
    int chapterNum;
    int lessonNum;
    int qNum;
    ArrayList<String> answers;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.text_question);

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
        TextView myProgress = (TextView) abLayout.findViewById(R.id.progress);
        Button homeButton = (Button) abLayout.findViewById(R.id.home);
        TextView titleBar = (TextView) abLayout.findViewById(R.id.actionBarTitle);

        myProgress.setVisibility(View.INVISIBLE);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Home");
                Intent newIntent = new Intent(TextQuestion.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);
            }
        });

        answerText = (EditText) findViewById(R.id.answerText);
        ImageView questionImage = (ImageView) findViewById(R.id.textQuestionImage);
        Button submit = (Button) findViewById(R.id.submitButton);

        // Create the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);


        chapterNum = getIntent().getExtras().getInt("ChapterNum");
        lessonNum = getIntent().getExtras().getInt("LessonNum");
        qNum = getIntent().getExtras().getInt("QuestionNum");
        answers = getIntent().getExtras().getStringArrayList("Answers");
        chapterTitle = getIntent().getExtras().getString("ChapterTitle");


        // Set the titleBar and the image
        titleBar.setText(chapterTitle);
        Drawables draw = new Drawables(chapterNum, lessonNum);
        int image = draw.getQuestionImage(qNum);
        questionImage.setImageResource(image);

        //getQuestionImage(questionImage);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Submit was clicked");

                String userAnswer = answerText.getText().toString();
                userAnswer = userAnswer.trim();

                if (userAnswer.equals(answers.get(0))) {
                    System.out.println("Correct Answer");
                    dialogBuilder.setMessage("Correct!");
                    setStaticArray(1, true);

                } else {
                    System.out.println("Incorrect Answer");
                    dialogBuilder.setMessage("Incorrect");
                    setStaticArray(-1, false);

                }

                dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        System.out.println("Inside OK");

                        finish();
                    }
                });

                dialog = dialogBuilder.create();
                dialog.show();

            }
        });


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
  /*  public void getQuestionImage(ImageView image) {
        System.out.println("Getting Question Image");

        switch (chapterNum) {

            case 1:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {

                            case 1:
                                image.setImageResource(R.drawable.c1_l1_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c1_l1_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c1_l1_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c1_l1_q4);
                                break;
                            case 5:
                                image.setImageResource(R.drawable.c1_l1_q5);
                                break;
                            case 6:
                                image.setImageResource(R.drawable.c1_l1_q6);
                                break;
                            case 7:
                                image.setImageResource(R.drawable.c1_l1_q7);
                                break;
                            default:
                                break;


                        }

                        break; // end Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c1_l2_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c1_l2_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c1_l2_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c1_l2_q4);
                                break;
                            case 5:
                                image.setImageResource(R.drawable.c1_l2_q5);
                                break;
                            default:
                                break;


                        }
                        break;

                    default:
                        break; // end Chapter 1 Lesson 2


                }
                break; // end Chapter 1

            case 2:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c2_l1_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c2_l1_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c2_l1_q3);
                                break;

                            default:
                                break;


                        }

                        break; // end Chapter 2 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c2_l2_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c2_l2_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c2_l2_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c2_l2_q4);
                                break;
                            case 5:
                                image.setImageResource(R.drawable.c2_l2_q5);
                                break;
                            case 6:
                                image.setImageResource(R.drawable.c2_l2_q6);
                                break;
                            case 7:
                                image.setImageResource(R.drawable.c2_l2_q7);
                                break;

                            default:
                                break; // end Chapter 2 Lesson 2


                        }
                        break;

                    case 3:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c2_l3_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c2_l3_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c2_l3_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c2_l3_q4);
                                break;

                            default:
                                break;

                        }
                        break;

                    default:
                        break; // end Chapter 2 Lesson 3

                }
                break;  // end Chapter 2

            case 3:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c3_l1_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c3_l1_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c3_l1_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c3_l1_q4);
                                break;
                            case 5:
                                image.setImageResource(R.drawable.c3_l1_q5);
                                break;

                            default:
                                break;


                        }

                        break; // end Chapter 3 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c3_l2_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c3_l2_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c3_l2_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c3_l2_q4);
                                break;
                            case 5:
                                image.setImageResource(R.drawable.c3_l2_q5);
                                break;

                            default:
                                break;


                        }
                        break;

                    default:
                        break; // end Chapter 3 Lesson 2

                }
                break;

            case 4:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c4_l1_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c4_l1_q2);
                                break;

                            default:
                                break;


                        }

                        break; // end Chapter 4 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c4_l2_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c4_l2_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c4_l2_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c4_l2_q4);
                                break;

                            default:
                                break;


                        }
                        break;  // end Chapter 4 Lesson 2

                    case 3:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c4_l3_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c4_l3_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c4_l3_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c4_l3_q4);
                                break;
                            case 5:
                                image.setImageResource(R.drawable.c4_l3_q5);
                                break;

                            default:
                                break;

                        }

                        break;  // end Chapter 4 Lesson 3

                    default:
                        break;

                }
                break;  // end Chapter 4

            case 5:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c5_l1_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c5_l1_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c5_l1_q3);
                                break;

                            default:
                                break;


                        }

                        break;  // end Chapter 5 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                image.setImageResource(R.drawable.c5_l2_q1);
                                break;
                            case 2:
                                image.setImageResource(R.drawable.c5_l2_q2);
                                break;
                            case 3:
                                image.setImageResource(R.drawable.c5_l2_q3);
                                break;
                            case 4:
                                image.setImageResource(R.drawable.c5_l2_q4);
                                break;
                            case 5:
                                image.setImageResource(R.drawable.c5_l2_q5);
                                break;

                            default:
                                break;


                        }
                        break;  // end Chapter 5 Lesson 2

                    default:
                        break;

                }
                break;

            default:

                break;


        }

    }

*/
    /*

    Set the buttons to green or red based on correct or incorrect answers

    */
    public void setStaticArray(int value, boolean correct) {
        switch (chapterNum) {

            case 1:
                switch (lessonNum) {
                    case 1:
                        QuestionList.c1l1[qNum-1] = value;

                        if (correct) {
                            QuestionList.numQuestionsCorrectC1L1++;
                        }

                        break; // end Lesson 1

                    case 2:
                        QuestionList.c1l2[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC1L2++;
                        }
                        break;

                    default:
                        break; // end Chapter 1 Lesson 2


                }
                break; // end Chapter 1

            case 2:
                switch (lessonNum) {
                    case 1:
                        QuestionList.c2l1[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC2L1++;
                        }
                        break; // end Chapter 2 Lesson 1

                    case 2:
                        QuestionList.c2l2[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC2L2++;
                        }
                        break;  // end Chapter 2 Lesson 2

                    case 3:
                        QuestionList.c2l3[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC2L3++;
                        }
                        break;

                    default:
                        break; // end Chapter 2 Lesson 3

                }
                break;  // end Chapter 2

            case 3:
                switch (lessonNum) {
                    case 1:
                        QuestionList.c3l1[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC3L1++;
                        }
                        break; // end Chapter 3 Lesson 1

                    case 2:
                        QuestionList.c3l2[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC3L2++;
                        }
                        break;

                    default:
                        break; // end Chapter 3 Lesson 2

                }
                break;

            case 4:
                switch (lessonNum) {
                    case 1:
                        QuestionList.c4l1[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC4L1++;
                        }
                        break; // end Chapter 4 Lesson 1

                    case 2:
                        QuestionList.c4l2[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC4L2++;
                        }
                        break;  // end Chapter 4 Lesson 2

                    case 3:
                        QuestionList.c4l3[qNum-1] = value;
                        if (correct) {
                            QuestionList.numQuestionsCorrectC4L3++;
                        }
                        break;  // end Chapter 4 Lesson 3

                    default:
                        break;

                }
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
