package com.project.cse110.geometryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by devinhickey on 1/31/16.
 */
public class LessonQuestion extends Activity {

    AlertDialog answerAlert;
    AlertDialog.Builder dialogBuilder;
    int correctAnswer;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.lesson_question);

        correctAnswer = getIntent().getExtras().getInt("answer");



        // Create the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);

        // Set the picture of the question
        ImageView questionImage = (ImageView) findViewById(R.id.imageView);
        questionImage.setImageResource(getIntent().getExtras().getInt("question"));

        // Create the buttons and onClicks
        Button figure1 = (Button) findViewById(R.id.mc1);
        figure1.setText(getIntent().getExtras().getString("first"));

        Button figure2 = (Button) findViewById(R.id.mc2);
        figure2.setText(getIntent().getExtras().getString("second"));

        Button figure3 = (Button) findViewById(R.id.mc3);
        figure3.setText(getIntent().getExtras().getString("third"));

        figure1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Figure 1 Clicked");

                if (correctAnswer == 1) {
                    dialogBuilder.setMessage("Correct!");
                    dialogBuilder.setNeutralButton("Next Question", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Start next intent with passed in extras
                            Intent newIntent = new Intent(LessonQuestion.this, LessonQuestion.class);
                            if (getIntent().getExtras().getInt("qNum") == 1) {
                                newIntent.putExtra("qNum", 2);
                                newIntent.putExtra("question", R.drawable.c1_l1_q2);
                                newIntent.putExtra("first", "A");
                                newIntent.putExtra("second", "B");
                                newIntent.putExtra("third", "C");
                                newIntent.putExtra("answer", 1);

                            } else {

                                newIntent.putExtra("qNum", 6);
                                newIntent.putExtra("question", R.drawable.c1_l1_q6);
                                newIntent.putExtra("first", "A Line");
                                newIntent.putExtra("second", "A Ray");
                                newIntent.putExtra("third", "A Line Segment");
                                newIntent.putExtra("answer", 2);

                            }

                            startActivity(newIntent);

                        }
                    });

                } else {
                    dialogBuilder.setMessage("Incorrect, try again");
                    dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                }
                answerAlert = dialogBuilder.create();
                answerAlert.show();
            }

        });

        figure2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Figure2 Clicked");

                if (correctAnswer == 2) {
                    dialogBuilder.setMessage("Correct!");
                    dialogBuilder.setNeutralButton("Next Question", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Start next intent with passed in extras
                            Intent newIntent = new Intent(LessonQuestion.this, LessonList.class);
                            startActivity(newIntent);
                        }
                    });
                } else {
                    dialogBuilder.setMessage("Incorrect, try again");
                    dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                }

                answerAlert = dialogBuilder.create();
                answerAlert.show();

            }
        });

        figure3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Figure3 Clicked");
                dialogBuilder.setMessage("Incorrect, try again");
                dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                answerAlert = dialogBuilder.create();
                answerAlert.show();
            }

        });


    }


    @Override
    public void onStop() {
        super.onStop();
        finish();

    }
}
