package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;

//import android.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.app.FragmentManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

/**
 * Created by devinhickey on 2/6/16.
 */
public class LessonDescription extends FragmentActivity {

    Intent thisIntent;
    Context ctx;
    String chapterTitle;
    String lessonTitle;
    ArrayList<String> lessonDescriptions;

    int currLesson;
    int chapterNum;
    int numQuestions;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        System.out.println("Inside LessonDescription onCreate");
        setContentView(R.layout.lesson_description);

        System.out.println("After SetContent View");
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        System.out.println("After VPager");
        vpPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        System.out.print("After setContent in OnCreate LD");

        thisIntent = this.getIntent();
        ctx = this;

        // Get extras
        chapterNum = getIntent().getExtras().getInt("ChapterNum");
        lessonTitle = getIntent().getExtras().getString("LessonTitle");
        currLesson = getIntent().getExtras().getInt("LessonNum");
        numQuestions = getIntent().getExtras().getInt("NumQuestions");
        chapterTitle = getIntent().getExtras().getString("ChapterTitle");
        lessonDescriptions = getIntent().getExtras().getStringArrayList("LessonDescription");
        // End get extras

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
        TextView myProgress = (TextView) abLayout.findViewById(R.id.progress);
        Button homeButton = (Button) abLayout.findViewById(R.id.home);
        TextView titleBar = (TextView) abLayout.findViewById(R.id.actionBarTitle);

        titleBar.setText(chapterTitle);

        myProgress.setVisibility(View.INVISIBLE);

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

        Button skipButton = (Button) findViewById(R.id.next);
        skipButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent questionIntent = new Intent(LessonDescription.this, QuestionList.class);

                questionIntent.putExtra("ChapterNum", chapterNum);
                questionIntent.putExtra("ChapterTitle", chapterTitle);
                questionIntent.putExtra("LessonTitle", lessonTitle);
                questionIntent.putExtra("LessonNum", currLesson);
                questionIntent.putExtra("NumQuestions", numQuestions);

                startActivity(questionIntent);

            }

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private int num_items;
        private ArrayList<String> lessonDescriptions = null;
        private ArrayList<Integer> pageImages = null;

        public MyPagerAdapter (FragmentManager fragmentManager) {
            super(fragmentManager);
            System.out.println("Inside PagerAdapter");
            lessonDescriptions = getIntent().getExtras().getStringArrayList("LessonDescription");
            pageImages = getIntent().getExtras().getIntegerArrayList("PageImages");
            if (lessonDescriptions != null) {
                num_items = lessonDescriptions.size();
            }
        }



        @Override
        public Fragment getItem(int position) {

            getIntent().putExtra("CurrentDescription", lessonDescriptions.get(position));
            getIntent().putExtra("CurrentImage", pageImages.get(position));

            return LessonFragment.newInstance(getIntent().getExtras());

        }

        @Override
        public int getCount() {
            return num_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "" + (position+1);

        }

    }

}
