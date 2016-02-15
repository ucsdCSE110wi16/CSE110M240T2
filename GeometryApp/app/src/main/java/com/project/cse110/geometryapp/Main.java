package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firebase.client.Firebase;

import com.firebase.client.Firebase;


/**
 * Created by devinhickey on 1/29/16.
 */

public class Main extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Firebase.setAndroidContext(this);
        System.out.println("Inside OnCreate main");

        setContentView(R.layout.content_main);

        ImageButton first = (ImageButton) findViewById(R.id.topic1);
        ImageButton second = (ImageButton) findViewById(R.id.topic2);



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
        homeButton.setVisibility(View.INVISIBLE);

        // end ActionBar



        first.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent topic1Intent = new Intent(Main.this, LessonList.class);

                        // Add the lessons
                        String lessonArray[] = new String[2];
                        lessonArray[0] = "Lines, Line Segments, Rays";
                        lessonArray[1] = "Points, Lines, Planes";
                        topic1Intent.putExtra("Lessons", lessonArray);

                        startActivity(topic1Intent);
                    }
                }
        );

        second.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent topic2Intent = new Intent(Main.this, LessonList.class);

                        // Add the lessons
                        String lessonArray[] = new String[2];
                        lessonArray[0] = "Lines, Line Segments, Rays";
                        lessonArray[1] = "Points, Lines, Planes";
                        topic2Intent.putExtra("Lessons", lessonArray);



                        startActivity(topic2Intent);

                    }
                }
        );

    }
}
