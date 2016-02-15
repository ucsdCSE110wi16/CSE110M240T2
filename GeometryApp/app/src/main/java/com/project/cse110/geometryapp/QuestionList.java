package com.project.cse110.geometryapp;

import android.app.Activity;
import android.os.Bundle;
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


    }
}
