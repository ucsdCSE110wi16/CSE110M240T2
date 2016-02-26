package com.project.cse110.geometryapp;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Migal on 2/22/2016.
 */
public class User {
    String uid;
    String email;
    Map data;
    Firebase ref;

    private User(){}

    public User(String uid, String email, Firebase ref){
        this.uid = uid;
        this.email = email;
        this.ref = ref;
        createData();
    }

    public String getUid(){return uid;}

    public String getEmail(){return email;}

    public Map getData(){return data;}

    public void setUid(String uid){this.uid = uid;}

    public void setEmail(String email){this.email = email;}

    private Map createLessons(int num_questions){
        Map lesson = new HashMap();
        int i = 1;
        lesson.put("complete", false);
        while (i <= num_questions){
            String q_number = "q"+Integer.toString(i);
            lesson.put(q_number, false);
            i++;
        }
        return lesson;
    }

    private Map createChapter(int num_lessons, int[] question_numbers){
        Map chapter = new HashMap();
        int i = 1;
        chapter.put("complete", false);
        while (i <= num_lessons){
            String l_number = "l"+Integer.toString(i);
            chapter.put(l_number, createLessons(question_numbers[i-1]));
            i++;
        }
        return chapter;
    }

    public void createData(){
        Map data = new HashMap();
        int[] lesson_numbers = {2,3,2,1};
        int[][] question_numbers = {new int[]{7,4},new int[]{3,7,4}, new int[]{5,5}, new int[]{5,4,5}};
        int i = 1;
        while (i <= 4){
            String c_number = "c"+Integer.valueOf(i);
            data.put(c_number, createChapter(lesson_numbers[i - 1], question_numbers[i - 1]));
            i++;
        }
        this.data = data;
    }

    public void updateQuestion(String chapter, String lesson, String question){
        ref.child("data/"+chapter+"/"+lesson+"/"+question).setValue(true);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User.this.data = (Map) dataSnapshot.child("data").getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("UpdateQuestion error: " + firebaseError.getMessage());
            }
        });
    }

    public void updateLesson(String chapter, String lesson){
        ref.child("data/"+chapter+"/"+lesson+"/complete").setValue(true);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User.this.data = (Map) dataSnapshot.child("data").getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("UpdateLesson error: " + firebaseError.getMessage());
            }
        });
    }
    public void updateChapter(String chapter){
        ref.child("data/"+chapter+"/complete").setValue(true);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User.this.data = (Map) dataSnapshot.child("data").getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("UpdateChapter error: " + firebaseError.getMessage());
            }
        });
    }

}
