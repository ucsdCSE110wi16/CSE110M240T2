package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
        System.out.println("Inside OnCreate main");
        setContentView(R.layout.activity_main);

        Button first = (Button) findViewById(R.id.topic1);
        Button second = (Button) findViewById(R.id.topic2);
        first.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent topic1Intent = new Intent(Main.this, topic1.class);
                        startActivity(topic1Intent);
                    }
                }
        );
        second.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                    }
                }
        );

    }
}
