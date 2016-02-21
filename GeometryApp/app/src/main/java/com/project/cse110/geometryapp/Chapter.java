package com.project.cse110.geometryapp;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Migal on 2/12/2016.
 */
public class Chapter {
    String title;
    int chapter_number;
    //Lesson lesson;
    Firebase chapter_ref;

    private Chapter(){}

    public Chapter (int chapter_number/*, int lesson_number*/){

        this.chapter_number = chapter_number;
        String refURL = "https://cse110geometry.firebaseio.com/chapters/"+"chapter"+String.valueOf(chapter_number);
        this.chapter_ref = new Firebase(refURL);
        this.chapter_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Inside Chapter Setting");
                Chapter.this.title =  (String) dataSnapshot.child("_title").getValue();
                System.out.println("Chapter: " + Chapter.this.title);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed:" + firebaseError.getMessage());
            }
        });
        //lesson(lesson_number);

    }

    public String getTitle(){
        System.out.println("Inside GetTitle");
        return title;
    }

}
