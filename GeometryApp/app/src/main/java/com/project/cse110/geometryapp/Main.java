package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firebase.client.Firebase;

import com.firebase.client.Firebase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by devinhickey on 1/29/16.
 */

public class Main extends Activity {

    DrawLine drawLine;

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

        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        drawLine = new DrawLine(this, first, second);
        this.addContentView(drawLine, param);
        //setContentView(drawLine);

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

        InputStream in = getResources().openRawResource(
                getResources().getIdentifier("chapters",
                        "raw", getPackageName()));

        ChapterXML chap = new ChapterXML(1, in);
        String title = chap.getTitle();
        int num = chap.getNumLessons();
        System.out.println(title + num);
        try {
            in.close();
        } catch (IOException e) {

        }

        in = getResources().openRawResource(
                getResources().getIdentifier("chapters",
                        "raw", getPackageName()));

        LessonXML less = new LessonXML(chap.getChapterNumber(), 1, in);
        title = less.getTitle();
        String body = less.getBody();
        num = less.getNumQuestions();
        System.out.println(title + num);
        System.out.println(body);

        try {
            in.close();
        } catch (IOException e) {

        }

        in = getResources().openRawResource(
                getResources().getIdentifier("chapters",
                        "raw", getPackageName()));

        QuestionXML q = new QuestionXML(chap.getChapterNumber(), less.getLessonNumber(), less.getTitle(), 1, in);
        System.out.println(q.getQuestionNumber());
        System.out.println(q.getQuestionType());
        System.out.println(q.getImageName());
        for (int i = 0; i < q.getResponses().size(); i++) {
            System.out.println(q.getResponses().get(i));
        }

        for (int i = 0; i < q.getAnswers().size(); i++) {
            System.out.println(q.getAnswers().get(i));
        }

        first.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent topic1Intent = new Intent(Main.this, LessonList.class);

                        // Add the lessons
                        String lessonArray[] = new String[2];
                        lessonArray[0] = "Lines, Line Segments, Rays";
                        lessonArray[1] = "Points, Lines, Planes";

                        String bodyArray[] = new String[2];
                        bodyArray[0] = "Lines: A line is a straight one-dimensional geometric object that extends infinitely in both directions.\nLine Segment: A line segment is a straight one-dimensional geometric object that has fixed starting and ending points.\nRay: A ray is a straight one-dimensional geometric object that has a fixed starting point, but extends infinitely.\n";
                        bodyArray[1] = "Point: A point is a geometric object that signifies a location, but has no size in itself. Lines, line segments, rays are all collections of points.\nPlane: A plane is a flat, two-dimensional surface that extends infinitely far. It is the two-dimensional analogue of a point (zero dimensions), a line (one dimension).\nCollinear Points: Points are collinear if they lie on the same line.\nCoplanar Points: Points are coplanar if they both lie on the same plane.\n";


//                        String[] q1 = new String[4];
//                        q1[0] = "1";
//                        q1[1] = "c1_l1_q1";
//                        q1[2] = "A,B,C";
//                        q1[3] = "1";
//
//                        String[] q2 = new String[4];
//                        q2[0] = "2";
//                        q2[1] = "c1_l1_q2";
//                        q2[2] = "A,B,C";
//                        q2[3] = "1";
//
//                        String[] q3 = new String[4];
//                        q3[0] = "3";
//                        q3[1] = "c1_l1_q6";
//                        q3[2] = "A line, A ray, A line";
//                        q3[3] = "2";
//
//
//                        String[][] question1 = {q1, q2, q3};
//
//                        q1 = new String[5];
//                        q1[0] = "1";
//                        q1[1] = "c1_l2_q1";
//                        q1[2] = "Yes,No";
//                        q1[3] = "1";
//                        q1[4] = "Are F, Y, W Coplanar?";
//
//                        q2 = new String[4];
//                        q2[0] = "2";
//                        q2[1] = "c1_l2_q2";
//                        q2[2] = "Yes,No";
//                        q2[3] = "1";
//                        q2[4] = "Are the points K,W and L collinear?";
//
//                        q3 = new String[4];
//                        q3[0] = "3";
//                        q3[1] = "c1_l2_q3";
//                        q3[2] = "Yes,No";
//                        q3[3] = "1";
//                        q3[4] = "Are the points O and B collinear?";
//
//                        String[][] question2 = {q1, q2, q3};
//
//                        String[][][] questions = {question1, question2};


                        //topic1Intent.putExtra("Questions", questions);
                        int[] qComplete = {0, 0, 0};
                        topic1Intent.putExtra("QuestionComplete", qComplete);
                        topic1Intent.putExtra("Lines, Line Segments, Rays", 0);
                        topic1Intent.putExtra("Points, Lines, Planes", 1);
                        topic1Intent.putExtra("LessonBody", bodyArray);
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
                        String lessonArray[] = new String[3];
                        lessonArray[0] = "Angles";
                        lessonArray[1] = "Intersecting Lines";
                        lessonArray[2] = "Parallel Lines";

                        String bodyArray[] = new String[3];
                        //bodyArray[0] = "";

                        topic2Intent.putExtra("Angles", 0);
                        topic2Intent.putExtra("Intersecting Lines", 1);
                        topic2Intent.putExtra("Parallel Lines", 2);
                        topic2Intent.putExtra("Lessons", lessonArray);


                        startActivity(topic2Intent);

                    }
                }
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Inside onResume");

    }
}
