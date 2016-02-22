package com.project.cse110.geometryapp;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Abhishek on 2/17/16.
 */
public class Question {
    long number;
    String title;
    String type;
    String answer;
    String image;
    String responses;

    private Question(){}

    public long getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getAnswer() {
        return answer;
    }

    public String getImage() {
        return image;
    }

    public String getResponses() {
        return responses;
    }

}
