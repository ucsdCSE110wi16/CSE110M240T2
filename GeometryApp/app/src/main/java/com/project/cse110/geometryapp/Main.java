package com.project.cse110.geometryapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by devinhickey on 1/29/16.
 */
public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        System.out.println("Inside OnCreate main");
        setContentView(R.layout.content_main);

        Button first = (Button) findViewById(R.id.firstButton);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("First Button Clicked");

            }
        });
    }

}
