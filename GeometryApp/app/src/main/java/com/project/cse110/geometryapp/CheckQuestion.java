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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devinhickey on 2/29/16.
 */
public class CheckQuestion extends Activity {

    Intent thisIntent;
    Context ctx;
    User myUser;

    AlertDialog dialog;
    AlertDialog.Builder dialogBuilder;
    ArrayList<CheckBox> checks;
    Button submitButton;

    String chapterTitle;
    int chapterNum;
    int lessonNum;
    int qNum;
    ArrayList<String> answers;
    ArrayList<String> responses;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.check_question);

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
                Intent newIntent = new Intent(CheckQuestion.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);
            }
        });


        chapterNum = getIntent().getExtras().getInt("ChapterNum");
        lessonNum = getIntent().getExtras().getInt("LessonNum");
        qNum = getIntent().getExtras().getInt("QuestionNum");
        answers = getIntent().getExtras().getStringArrayList("Answers");
        chapterTitle = getIntent().getExtras().getString("ChapterTitle");
        responses = getIntent().getExtras().getStringArrayList("Responses");

        // Create the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);

        // Set the titleBar and the image
        titleBar.setText(chapterTitle);
        Drawables draw = new Drawables(chapterNum, lessonNum);
        int image = draw.getQuestionImage(qNum);

        ImageView imageView = (ImageView) findViewById(R.id.checkImage);
        imageView.setImageResource(image);

        checks = new ArrayList<>();

        // Create MC buttons
        for (int i = 0; i < responses.size(); i++) {
            CheckBox newCheck = new CheckBox(this);
            checks.add(newCheck);

            LinearLayout myLayout;

            if (i < 2) {
                myLayout = (LinearLayout) findViewById(R.id.topBoxes);
            } else {
                myLayout = (LinearLayout) findViewById(R.id.bottomBoxes);
            }

            LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            layParam.setMargins(10, 10, 10, 10);

            // Add the buttons with the layout params
            myLayout.addView(newCheck, layParam);

            newCheck.setText(responses.get(i));
            newCheck.setTextSize(20);

        }

        submitButton = (Button) findViewById(R.id.checkSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numAnswers = 0;
                int numChecks = 0;

                for (int i = 0; i < checks.size(); i++) {
                    CheckBox myCheck = checks.get(i);
                    if (myCheck.isChecked()) {
                        numChecks++;
                        for (int j = 0; j < answers.size(); j++) {
                            String ans = answers.get(j);
                            if (ans.equals(myCheck.getText())) {
                                numAnswers++;
                                break;
                            }
                        }
                    }
                }

                if ((numAnswers == numChecks) && (numAnswers == answers.size())) {
                    System.out.print("All Correct Answers Clicked");
                    dialogBuilder.setMessage("Correct!");
                    try {
                        myUser.updateQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum), true);
                    } catch(Exception e) {}
                } else {
                    dialogBuilder.setMessage("Incorrect!");
                    try {
                        myUser.updateQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum), false);
                    } catch (Exception e) {}
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

        checkComplete();


    }

    @Override
    public void onStop() {
        super.onStop();
        finish();

    }

    /*
        Check if the question has been completed.
        Select the correct answers and

     */
    public void checkComplete() {

        int done = myUser.retrieveQuestion(Integer.toString(chapterNum), Integer.toString(lessonNum), Integer.toString(qNum));

        if (done == 1) {

            // Check every CheckBox
            for (int i = 0; i < checks.size(); i++) {

                CheckBox currBox = checks.get(i);

                // Check for every answer
                for (int j = 0; j < answers.size(); j++) {
                    String ans = answers.get(j);

                    if (currBox.getText().equals(ans)) {

                        currBox.setChecked(true);

                    }
                }

                currBox.setClickable(false);

            }

            submitButton.setClickable(false);
        }

    }

}
