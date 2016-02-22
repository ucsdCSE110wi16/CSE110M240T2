package com.project.cse110.geometryapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Migal on 2/17/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lesson {
    String title;
    String body;
    //String baseUrl;
    int lesson_number;
    boolean test;
    Question question[];
    Firebase lesson_ref;

    private Lesson(){}

    public String getTitle(){ return this.title; }

    public String getBody(){
        return body;
    }

    public int getLesson_number(){
        return lesson_number;
    }

    public boolean getTest() {return test;}

    public void setLesson_ref(Firebase lesson_ref){
        this.lesson_ref = lesson_ref;
    }

    public void setLessonTest(boolean result){
        this.test = result;
        System.out.println("LESSON REF " + lesson_ref);
        lesson_ref.child("test").setValue(this.test);
    }
}
