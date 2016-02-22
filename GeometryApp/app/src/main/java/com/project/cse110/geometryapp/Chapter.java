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
    long lesson_numbers;
    Lesson lesson[];
    Firebase chapter_ref;

    private Chapter(){}

    public Chapter (int chapter_number){

        this.chapter_number = chapter_number;
        String refURL = "https://cse110geometry.firebaseio.com/chapters/"+"chapter"+String.valueOf(chapter_number);
        this.chapter_ref = new Firebase(refURL);
        this.chapter_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Chapter.this.title = (String) dataSnapshot.child("title").getValue();
                Chapter.this.lesson_numbers = (long) dataSnapshot.child("lesson").getChildrenCount();
                Chapter.this.lesson = new Lesson[(int) Chapter.this.lesson_numbers];
                int i = 0;
                for (DataSnapshot lessonSnapshot: dataSnapshot.child("lesson").getChildren()){
                    Chapter.this.lesson[i] = lessonSnapshot.getValue(Lesson.class);
                    System.out.println("CHAPTER PLACE REF "+lessonSnapshot.getRef());
                    Chapter.this.lesson[i].setLesson_ref(lessonSnapshot.getRef());
                    //System.out.println("NUMBER OF QUES " + (int) lessonSnapshot.child("question").getChildrenCount());
                    Chapter.this.lesson[i].question = new Question[(int) lessonSnapshot.child("question").getChildrenCount()];
                    int j = 0;
                    for (DataSnapshot questionSnapshot: lessonSnapshot.child("question").getChildren()){
                        Chapter.this.lesson[i].question[j] = questionSnapshot.getValue(Question.class);
                        System.out.println(Chapter.this.lesson[i].question[j].getImage());
                        j++;
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed:" + firebaseError.getMessage());
            }
        });
        //this.lesson= new Lesson(lesson_number, refURL);

    }

    public String getTitle(){
        return title;
    }

    public int getChapter_number(){
        return chapter_number;
    }

    public long getLesson_numbers(){
        return lesson_numbers;
    }

    public Lesson getLesson(int lesson_number){
        return lesson[lesson_number-1];
    }

}
