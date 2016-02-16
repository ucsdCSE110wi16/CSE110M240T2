package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by devinhickey on 1/31/16.
 */
public class LessonQuestion extends Activity {

    AlertDialog answerAlert;
    AlertDialog.Builder dialogBuilder;
    int correctAnswer;
    int lessonNum;
    int qNum;
    String answer;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.lesson_question);

        correctAnswer = getIntent().getExtras().getInt("answer");
        lessonNum = this.getIntent().getExtras().getInt("Lesson");
        answer = this.getIntent().getExtras().getString("Answer");

        qNum = this.getIntent().getExtras().getInt("QNUM");

        System.out.println("Lesson Num: " + lessonNum);


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
                System.out.println("Home");
                Intent newIntent = new Intent(LessonQuestion.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);
            }
        });

        // end ActionBar


        // Create the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);

        // Set the picture of the question
        ImageView questionImage = (ImageView) findViewById(R.id.imageView);
        questionImage.setImageResource(getIntent().getExtras().getInt("PNG"));

        if (lessonNum == 2) {

            TextView textV = (TextView) findViewById(R.id.textView);
//            LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout);
//            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//
//            myLayout.addView(textV, layParam);

            String text = this.getIntent().getExtras().getString("Body");

            System.out.println(text);
            textV.setText(text);
            //textV.setTextSize(25);

        } else {
            TextView textV = (TextView) findViewById(R.id.textView);

            textV.setVisibility(TextView.GONE);

        }

        String[] buttons = this.getIntent().getExtras().getStringArray("Buttons");

        for (int i = 0; i < buttons.length; i++) {
            final Button newButton = new Button(this);
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearButtonLayout);
            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            // Add the buttons with the layout params
            myLayout.addView(newButton, layParam);

            newButton.setText(buttons[i]);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String buttonText = (String) newButton.getText();
                    if (answer.equals(buttonText)) {
                        System.out.println("Correct Answer");

                        if (lessonNum == 1) {
                            if (qNum == 1) {
                                QuestionList.l1q1 = 1;
                            } else if (qNum == 2) {
                                QuestionList.l1q2 = 1;
                            } else if (qNum == 3) {
                                QuestionList.l1q3 = 1;
                            }
                        } else if (lessonNum == 2) {
                            if (qNum == 1) {
                                QuestionList.l2q1 = 1;
                            } else if (qNum == 2) {
                                QuestionList.l2q2 = 1;
                            } else if (qNum == 3) {
                                QuestionList.l2q3 = 1;
                            }
                        }

                        dialogBuilder.setMessage("Correct!");


                    } else {

                        System.out.println("Incorrect Answer");

                        if (lessonNum == 1) {
                            if (qNum == 1) {
                                QuestionList.l1q1 = 2;
                            } else if (qNum == 2) {
                                QuestionList.l1q2 = 2;
                            } else if (qNum == 3) {
                                QuestionList.l1q3 = 2;
                            }
                        } else if (lessonNum == 2) {
                            if (qNum == 1) {
                                QuestionList.l2q1 = 2;
                            } else if (qNum == 2) {
                                QuestionList.l2q2 = 2;
                            } else if (qNum == 3) {
                                QuestionList.l2q3 = 2;
                            }
                        }

                        dialogBuilder.setMessage("Incorrect!");

                    }

                    dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            System.out.println("Inside OK");

                            finish();

//                            // Start next intent with passed in extras
//                            Intent newIntent = new Intent(LessonQuestion.this, QuestionList.class);
//
//                            newIntent.putExtra("Lesson", lessonNum);
                        }
                    });

                    answerAlert = dialogBuilder.create();
                    answerAlert.show();
                }
            });

        }


        // Create the buttons and onClicks
//        Button figure1 = (Button) findViewById(R.id.mc1);
//        figure1.setText(getIntent().getExtras().getString("first"));
//
//        Button figure2 = (Button) findViewById(R.id.mc2);
//        figure2.setText(getIntent().getExtras().getString("second"));
//
//        Button figure3 = (Button) findViewById(R.id.mc3);
//        figure3.setText(getIntent().getExtras().getString("third"));
//
//        figure1.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                System.out.println("Figure 1 Clicked");
//
//                if (correctAnswer == 1) {
//                    dialogBuilder.setMessage("Correct!");
//                    dialogBuilder.setNeutralButton("Next Question", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            // Start next intent with passed in extras
//                            Intent newIntent = new Intent(LessonQuestion.this, LessonQuestion.class);
//                            if (getIntent().getExtras().getInt("qNum") == 1) {
//                                newIntent.putExtra("qNum", 2);
//                                newIntent.putExtra("question", R.drawable.c1_l1_q2);
//                                newIntent.putExtra("first", "A");
//                                newIntent.putExtra("second", "B");
//                                newIntent.putExtra("third", "C");
//                                newIntent.putExtra("answer", 1);
//
//                            } else {
//
//                                newIntent.putExtra("qNum", 6);
//                                newIntent.putExtra("question", R.drawable.c1_l1_q6);
//                                newIntent.putExtra("first", "A Line");
//                                newIntent.putExtra("second", "A Ray");
//                                newIntent.putExtra("third", "A Line Segment");
//                                newIntent.putExtra("answer", 2);
//
//                            }
//
//                            startActivity(newIntent);
//
//                        }
//                    });
//
//                } else {
//                    dialogBuilder.setMessage("Incorrect, try again");
//                    dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                }
//                answerAlert = dialogBuilder.create();
//                answerAlert.show();
//            }
//
//        });
//
//        figure2.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                System.out.println("Figure2 Clicked");
//
//                if (correctAnswer == 2) {
//                    dialogBuilder.setMessage("Correct!");
//                    dialogBuilder.setNeutralButton("Next Question", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            // Start next intent with passed in extras
//                            Intent newIntent = new Intent(LessonQuestion.this, LessonList.class);
//                            startActivity(newIntent);
//                        }
//                    });
//                } else {
//                    dialogBuilder.setMessage("Incorrect, try again");
//                    dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                }
//
//                answerAlert = dialogBuilder.create();
//                answerAlert.show();
//
//            }
//        });
//
//        figure3.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                System.out.println("Figure3 Clicked");
//                dialogBuilder.setMessage("Incorrect, try again");
//                dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                answerAlert = dialogBuilder.create();
//                answerAlert.show();
//            }
//
//        });


    }


    @Override
    public void onStop() {
        super.onStop();
        //finish();

    }
}
