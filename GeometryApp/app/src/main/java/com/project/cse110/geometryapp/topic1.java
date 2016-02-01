package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by devinhickey on 1/29/16.
 */
public class topic1 extends Activity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.topic1_content);

        Button lesson1Button = (Button) findViewById(R.id.lesson1);

        lesson1Button.setOnClickListener(
                new Button.OnClickListener() {
            public void onClick(View v){
                Intent newIntent = new Intent(topic1.this, Lesson.class);
                startActivity(newIntent);

            }

        });
    }
}