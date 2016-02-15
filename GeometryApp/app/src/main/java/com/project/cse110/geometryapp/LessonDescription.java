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
import android.widget.TextView;

/**
 * Created by devinhickey on 2/6/16.
 */
public class LessonDescription extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        System.out.println("Inside LessonDescription onCreate");
        setContentView(R.layout.lesson_description);


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
                System.out.println("Home Button");
                Intent newIntent = new Intent(LessonDescription.this, Main.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(newIntent);
            }
        });

        // end ActionBar


        String body = getIntent().getExtras().getString("body");
        TextView text = (TextView) findViewById(R.id.textView);

        text.setText(body);

        Button skipButton = (Button) findViewById(R.id.skip);
        skipButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent questionIntent = new Intent(LessonDescription.this, QuestionList.class);

                /*
                int question = R.drawable.c1_l1_q1;
                questionIntent.putExtra("question", question);

                String response1 = "A";
                questionIntent.putExtra("first", response1);

                String response2 = "B";
                questionIntent.putExtra("second", response2);

                String response3 = "C";
                questionIntent.putExtra("third", response3);

                int answer = 1;
                questionIntent.putExtra("answer", answer);

                int qNum = 1;
                questionIntent.putExtra("qNum", qNum);
*/

                startActivity(questionIntent);

            }

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();

    }
}
