package com.project.cse110.geometryapp;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

/**
 * Created by devinhickey on 2/1/16.
 * Migal, create update and pull functions for xml
 */
public class Utility {
    String chapter;

    public Utility(int number){
        chapter = "chapter"+String.valueOf(number);
    }

    public void setChapter(int number){
        chapter = "chapter"+String.valueOf(number);
    }


    public String getChapterTitle(){
        String refURL = "https://cse110geometry.firebaseio.com/chapters/"+this.chapter;
        Firebase FirebaseRef = new Firebase(refURL);
        Query queryRef = FirebaseRef.orderByChild("_title").once(
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot == null || snapshot.getValue() == null)
                    return null;
                else
                    return snapshot.getValue().toString();
            }
            @Override
            public void onCancelled(FirebaseError error) {
                System.out.println("The read failed:" + error.getMessage());
            }
        });
    }
    public String getLessonTitle(int number){
        String refURL = "https://cse110geometry.firebaseio.com/chapters/"+this.chapter+"/lesson/"+String.valueOf(number-1);
        Firebase FirebaseRef = new Firebase(refURL);
        Query queryRef = FirebaseRef.orderByChild("_title");
        String ret;
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot == null || snapshot.getValue() == null) {
                    ret =;
                }
                else{
                    return snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(FirebaseError error) {
                System.out.println("The read failed:" + error.getMessage());
            }
        });
    }
}
