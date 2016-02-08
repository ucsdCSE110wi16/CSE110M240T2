package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by devinhickey on 1/29/16.
 */
public class LessonList extends Activity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        System.out.println("Inside LessonList onCreate");
        setContentView(R.layout.lesson_list);

        Button lesson1Button = (Button) findViewById(R.id.lesson1);
        lesson1Button.setText("Lines, line segments, and rays");
        final Button lesson2Button = (Button) findViewById(R.id.lesson2);
        lesson2Button.setText("Points, lines, and planes");


        lesson1Button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent newIntent = new Intent(LessonList.this, LessonDescription.class);
                        // Grab text from xml FIXME
                        String intentString = "Lines: A line is a straight one-dimensional geometric object that extends infinitely in both directions.\n"
                                +
                                "Line Segment: A line segment is a straight one-dimensional geometric object that has fixed starting and ending points.\n"
                                +
                                "Ray: A ray is a straight one-dimensional geometric object that has a fixed starting point, but extends infinitely.";

                        newIntent.putExtra("body", intentString);

                        startActivity(newIntent);

                    }

                });

        lesson2Button.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    Intent newIntent = new Intent(LessonList.this, LessonDescription.class);

                    String intentString = "Other Description";
                    newIntent.putExtra("body", intentString);
                    startActivity(newIntent);

                }

        });
    }
}
