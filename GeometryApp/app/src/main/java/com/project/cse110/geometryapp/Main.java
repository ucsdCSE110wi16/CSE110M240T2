package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
        first.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent topic1Intent = new Intent(Main.this, LessonList.class);
                        startActivity(topic1Intent);
                    }
                }
        );
        second.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                    }
                }
        );

    }
}
