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

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.question_list);


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
        Button myButton = new Button(this);
        myButton.setText("First Question");

        Button secButton = new Button(this);
        secButton.setText("Second Question");

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.questionLayout);
        LinearLayout.LayoutParams layParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Add the buttons with the layout params
        myLayout.addView(myButton, layParam);
        myLayout.addView(secButton, layParam);

        // Create the button onClicks
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Inside first button click");

            }
        });

        secButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Inside second Button onClick");
            }
        });

    }
}
