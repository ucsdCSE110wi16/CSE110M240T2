package com.project.cse110.geometryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by devinhickey on 1/31/16.
 */
public class LessonQuestion extends Activity {

    AlertDialog answerAlert;
    AlertDialog.Builder dialogBuilder;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.lesson_question);

        // Create the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Create the buttons and onClicks
        Button figure1 = (Button) findViewById(R.id.figure1);
        Button figure2 = (Button) findViewById(R.id.figure2);
        Button figure3 = (Button) findViewById(R.id.figure3);
        Button figure4 = (Button) findViewById(R.id.figure4);

        figure1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Figure 1 Clicked");
                dialogBuilder.setMessage("Incorrect, try again");
                answerAlert = dialogBuilder.create();
                answerAlert.show();
            }

        });

        figure2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Figure2 Clicked");
                dialogBuilder.setMessage("Incorrect, try again");
                answerAlert = dialogBuilder.create();
                answerAlert.show();
            }

        });

        figure3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Figure3 Clicked");
                dialogBuilder.setMessage("Incorrect, try again");
                answerAlert = dialogBuilder.create();
                answerAlert.show();
            }

        });

        figure4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Figure4 Clicked");
                dialogBuilder.setMessage("Correct!");
                answerAlert = dialogBuilder.create();
                answerAlert.show();
            }

        });


    }
}
