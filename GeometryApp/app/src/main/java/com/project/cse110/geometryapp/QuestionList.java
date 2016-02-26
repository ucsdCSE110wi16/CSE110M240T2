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
    TextView myProgress;
    String lessonTitle;
    String chapterTitle;
    int chapterNum;
    int numQuestions;
    int lessonNum;
    int qNum;

    static int numQuestionsCorrectC1L1 = 0;
    static int numQuestionsCorrectC1L2 = 0;
    static int numQuestionsCorrectC2L1 = 0;
    static int numQuestionsCorrectC2L2 = 0;
    static int numQuestionsCorrectC2L3 = 0;
    static int numQuestionsCorrectC3L1 = 0;
    static int numQuestionsCorrectC3L2 = 0;
    static int numQuestionsCorrectC4L1 = 0;
    static int numQuestionsCorrectC4L2 = 0;
    static int numQuestionsCorrectC4L3 = 0;


    ArrayList<Button> myButtons = new ArrayList<Button>();
    static int[] c1l1 = {0, 0, 0, 0, 0, 0, 0};
    static int[] c1l2 = {0, 0, 0, 0};
    static int[] c2l1 = {0, 0, 0};
    static int[] c2l2 = {0, 0, 0, 0, 0, 0, 0};
    static int[] c2l3 = {0, 0, 0, 0};
    static int[] c3l1 = {0, 0, 0, 0, 0};
    static int[] c3l2 = {0, 0, 0, 0, 0};
    static int[] c4l1 = {0, 0, 0, 0, 0};
    static int[] c4l2 = {0, 0, 0, 0};
    static int[] c4l3 = {0, 0, 0, 0, 0};


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.question_list);

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

        for (int i = 0; i < numQuestions; i++) {

            Button newButton = new Button(this);
            myButtons.add(newButton);
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.questionLayout);
            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            myLayout.addView(newButton, layParam);
            newButton.setText("Question " + (i+1));
            checkDone(newButton, i);

            qNum = i+1;

            newButton.setOnClickListener(new View.OnClickListener() {
                int currQuestion = qNum;
                @Override
                public void onClick(View v) {

                    QuestionXML qXML = new QuestionXML(chapterNum, lessonNum, lessonTitle, currQuestion, ctx);

                    ArrayList<String> responses = qXML.getResponses();
                    ArrayList<String> answers = qXML.getAnswers();
                    String image = qXML.getImageName();
                    String questionType = qXML.getQuestionType();

                    Intent newIntent;

                    if (questionType.equals("MCQ")) {

                       newIntent = new Intent(QuestionList.this, MCQuestion.class);
                        newIntent.putExtra("Responses", responses);

                    } else if (questionType.equals("Text")) {

                        newIntent = new Intent(QuestionList.this, TextQuestion.class);

                    } else {

                        System.out.println("Check Question Clicked");
                        return;

                    }

                    newIntent.putExtra("ChapterNum", chapterNum);
                    newIntent.putExtra("ChapterTitle", chapterTitle);
                    newIntent.putExtra("LessonNum", lessonNum);
                    newIntent.putExtra("LessonTitle", lessonTitle);
                    newIntent.putExtra("QuestionNum", currQuestion);
                    newIntent.putExtra("QuestionImage", image);
                    newIntent.putExtra("QuestionType", questionType);
                    newIntent.putExtra("Answers", answers);


                    startActivity(newIntent);



                }
            });

        }


//        if (lessonNum == 1) {
//            if (l1q1 == 1) {
//                firstButton.setBackgroundColor(Color.GREEN);
//
//            } else if (l1q1 == 2) {
//                firstButton.setBackgroundColor(Color.RED);
//
//            } else {
//
//                firstButton.setBackgroundColor(Color.LTGRAY);
//
//            }
//        }

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



//        // Create the button onClicks
//        firstButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Inside first button click");
//
//                int png;
//                int qNum = 1;
//                String[] buttons;
//                String body;
//                String answer;
//                Intent newIntent = new Intent(QuestionList.this, MCQuestion.class);
//
//                if (lessonNum == 1) {
//
//                    buttons = new String[3];
//                    buttons[0] = "A";
//                    buttons[1] = "B";
//                    buttons[2] = "C";
//
//                    answer = "A";
//
//                    png = R.drawable.c1_l1_q1;
//
//
//
//
//                } else {
//
//                    png = R.drawable.c1_l2_q1;
//                    body = "Are F,Y,W Coplanar?";
//                    newIntent.putExtra("Body", body);
//
//                    buttons = new String[2];
//                    buttons[0] = "Yes";
//                    buttons[1] = "No";
//
//                    answer = "Yes";
//
//                }
//
//                newIntent.putExtra("QNUM", qNum);
//                newIntent.putExtra("Answer", answer);
//                newIntent.putExtra("Buttons", buttons);
//                newIntent.putExtra("PNG", png);
//                newIntent.putExtra("Lesson", lessonNum);
//
//                startActivity(newIntent);
//
//            }
//        });
//
//        secButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Inside second Button onClick");
//
//                int png;
//                String body;
//                String answer;
//                String[] buttons;
//                Intent newIntent = new Intent(QuestionList.this, MCQuestion.class);
//
//                if (lessonNum == 1) {
//                    png = R.drawable.c1_l1_q2;
//
//                    buttons = new String[3];
//                    buttons[0] = "A";
//                    buttons[1] = "B";
//                    buttons[2] = "C";
//
//                    answer = "A";
//
//                } else {
//                    png = R.drawable.c1_l2_q2;
//
//                    body = "Are the points K,W and L collinear?";
//                    newIntent.putExtra("Body", body);
//
//                    buttons = new String[2];
//                    buttons[0] = "Yes";
//                    buttons[1] = "No";
//
//                    answer = "Yes";
//
//                }
//
//                newIntent.putExtra("QNUM", 2);
//                newIntent.putExtra("Answer", answer);
//                newIntent.putExtra("Buttons", buttons);
//                newIntent.putExtra("PNG", png);
//                newIntent.putExtra("Lesson", lessonNum);
//
//                startActivity(newIntent);
//
//            }
//        });
//
//        thirdButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int png;
//                String body;
//                String answer;
//                String[] buttons;
//                Intent newIntent = new Intent(QuestionList.this, MCQuestion.class);
//
//                if (lessonNum == 1) {
//
//                    png = R.drawable.c1_l1_q6;
//
//                    buttons = new String[3];
//                    buttons[0] = "A Line";
//                    buttons[1] = "A Ray";
//                    buttons[2] = "A Line Segment";
//
//                    answer = "A Ray";
//
//                } else {
//
//                    png = R.drawable.c1_l2_q3;
//                    body = "Are the points O and B collinear?";
//                    newIntent.putExtra("Body", body);
//
//                    buttons = new String[2];
//                    buttons[0] = "Yes";
//                    buttons[1] = "No";
//
//                    answer = "Yes";
//
//                }
//
//                newIntent.putExtra("QNUM", 3);
//                newIntent.putExtra("Answer", answer);
//                newIntent.putExtra("Buttons", buttons);
//                newIntent.putExtra("Lesson", lessonNum);
//                newIntent.putExtra("PNG", png);
//
//                startActivity(newIntent);
//
//            }
//        });

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Onresume");
        for (int i = 0; i < myButtons.size(); i++) {
            checkDone(myButtons.get(i), i);
        }




//        Button firstButton = (Button) findViewById(R.id.ques1);
//        Button secButton = (Button) findViewById(R.id.ques2);
//        Button thirdButton = (Button) findViewById(R.id.ques3);
//
//        if (lessonNum == 1) {
//            if (l1q1 == 1) {
//                firstButton.setBackgroundColor(Color.GREEN);
//
//            } else if (l1q1 == 2) {
//                firstButton.setBackgroundColor(Color.RED);
//
//            } else {
//
//                firstButton.setBackgroundColor(Color.LTGRAY);
//
//            }
//
//
//            if (l1q2 == 1) {
//
//                secButton.setBackgroundColor(Color.GREEN);
//
//            } else if (l1q2 == 2) {
//
//                secButton.setBackgroundColor(Color.RED);
//
//            } else {
//
//                secButton.setBackgroundColor(Color.LTGRAY);
//
//            }
//
//            if (l1q3 == 1) {
//
//                thirdButton.setBackgroundColor(Color.GREEN);
//
//            } else if (l1q3 == 2) {
//
//                thirdButton.setBackgroundColor(Color.RED);
//
//            } else {
//
//
//                thirdButton.setBackgroundColor(Color.LTGRAY);
//            }
//
//
//        } else {
//
//            if (l2q1 == 1) {
//
//                firstButton.setBackgroundColor(Color.GREEN);
//
//            } else if (l2q1 == 2) {
//
//
//                firstButton.setBackgroundColor(Color.RED);
//            } else {
//
//                firstButton.setBackgroundColor(Color.LTGRAY);
//
//            }
//
//            if (l2q2 == 1) {
//
//                secButton.setBackgroundColor(Color.GREEN);
//
//            } else if (l2q2 == 2) {
//
//                secButton.setBackgroundColor(Color.RED);
//
//            } else {
//
//                secButton.setBackgroundColor(Color.LTGRAY);
//            }
//
//            if (l2q3 == 1) {
//
//                thirdButton.setBackgroundColor(Color.GREEN);
//
//            } else if (l2q3 == 2) {
//
//                thirdButton.setBackgroundColor(Color.RED);
//
//            } else {
//
//
//                thirdButton.setBackgroundColor(Color.LTGRAY);
//            }
//
//        }

    }

    /*
        Set the button colors based on correct or incorrect

     */
    public void checkDone(Button newButton, int index) {

        String myString;
        switch (chapterNum) {

            case 1:
                switch (lessonNum) {
                    case 1:
                        if (c1l1[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c1l1[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC1L1 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break; // end Lesson 1

                    case 2:
                        if (c1l2[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c1l2[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC1L2 + "/" + numQuestions;
                        myProgress.setText(myString);
                        break;

                    default:
                        break; // end Chapter 1 Lesson 2


                }
                break; // end Chapter 1

            case 2:
                switch (lessonNum) {
                    case 1:
                        if (c2l1[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c2l1[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }
                        myString = numQuestionsCorrectC2L1 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break; // end Chapter 2 Lesson 1

                    case 2:
                        if (c2l2[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c2l2[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC2L2 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break;  // end Chapter 2 Lesson 2

                    case 3:
                        if (c2l3[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c2l3[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC2L3 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break;

                    default:
                        break; // end Chapter 2 Lesson 3

                }
                break;  // end Chapter 2

            case 3:
                switch (lessonNum) {
                    case 1:
                        if (c3l1[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c3l1[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC3L1 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break; // end Chapter 3 Lesson 1

                    case 2:
                        if (c3l2[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c3l2[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC3L2 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break;

                    default:
                        break; // end Chapter 3 Lesson 2

                }
                break;

            case 4:
                switch (lessonNum) {
                    case 1:
                        if (c4l1[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c4l1[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC4L1 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break; // end Chapter 4 Lesson 1

                    case 2:
                        if (c4l2[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c4l2[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC4L2 + "/" + numQuestions;
                        myProgress.setText(myString);

                        break;  // end Chapter 4 Lesson 2

                    case 3:
                        if (c4l3[index] == 1) {
                            newButton.setBackgroundColor(Color.GREEN);
                        } else if (c4l3[index] == -1) {
                            newButton.setBackgroundColor(Color.RED);
                        }

                        myString = numQuestionsCorrectC4L3 + "/" + numQuestions;
                        myProgress.setText(myString);

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
    } // end CheckDone


}
