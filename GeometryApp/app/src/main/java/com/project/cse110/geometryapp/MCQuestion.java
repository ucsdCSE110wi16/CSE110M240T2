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
    ArrayList<Button> myButtons;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.mcq_question);

        thisIntent = this.getIntent();
        ctx = this;
        myUser = new User();

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

        myButtons = new ArrayList<>();

        // Create MC buttons
        for (int i = 0; i < responses.size(); i++) {
            Button newButton = new Button(this);
            myButtons.add(newButton);

            newButton.setTextColor(getResources().getColor(R.color.white));
            newButton.setBackgroundColor(getResources().getColor(R.color.peter));

            LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearButtonLayout);
            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            layParam.setMargins(10, 10, 10, 10);
            myLayout.setWeightSum(responses.size());

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
                        try {
                            myUser.updateQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum), true);
                        } catch(Exception e) {}
                    } else {
                        System.out.println("Incorrect Answer");
                        dialogBuilder.setMessage("Incorrect!");
                        try {
                            myUser.updateQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum), false);
                        } catch(Exception e) {}
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

        checkComplete();

    }


    @Override
    public void onStop() {
        super.onStop();
        finish();

    }

    /*
        Checks if the question has been completed correctly
     */
    private void checkComplete() {


        int done = myUser.retrieveQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum));
        System.out.println("Done Value: " + done);

        // Check for complete
        if (done == 1) {

            for (int i = 0; i < myButtons.size(); i++) {
                Button currButton = myButtons.get(i);
                currButton.setClickable(false);

                if ((i+1) == correctAnswer ) {
                    currButton.setBackgroundColor(getResources().getColor(R.color.green));
                }

            }

        }

    }


}
