package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"ref"})
public class User {
    String uid;
    String email;
    public static Map data;
    private static Firebase ref;

    public User(){}

    public User(Firebase ref){
        this.ref = ref;
    }

    public User(String uid, String email, Firebase ref){
        this.uid = uid;
        this.email = email;
        this.ref = ref;
        createData();
    }

    public String getUid(){return uid;}

    public String getEmail(){return email;}

    public Firebase getRef(){return ref;}

    public Map getData(){return data;}

    public void setUid(String uid){this.uid = uid;}

    public void setEmail(String email){this.email = email;}

    private Map createLessons(int num_questions){
        Map lesson = new HashMap();
        int i = 1;
        lesson.put("complete", "0");
        while (i <= num_questions){
            String q_number = "q"+Integer.toString(i);
            lesson.put(q_number, "0");
            i++;
        }
        return lesson;
    }

    private Map createChapter(int num_lessons, int[] question_numbers){
        Map chapter = new HashMap();
        int i = 1;
        chapter.put("complete", "0");
        while (i <= num_lessons){
            String l_number = "l"+Integer.toString(i);
            chapter.put(l_number, createLessons(question_numbers[i - 1]));
            i++;
        }
        return chapter;
    }

    public void createData(){
        Map data = new HashMap();
        int[] lesson_numbers = {2,3,2,3,2};
        int[][] question_numbers = {new int[]{7,4},new int[]{3,7,4}, new int[]{5,5}, new int[]{5,4,5}, new int[]{3,5}};
        int i = 1;
        while (i <= 5){
            String c_number = "c"+Integer.valueOf(i);
            data.put(c_number, createChapter(lesson_numbers[i - 1], question_numbers[i - 1]));
            i++;
        }
        this.data = data;
    }

    public void updateQuestion(String chapter, String lesson, String question, boolean answer){
        Map lessonMap = retrieveLessonMap(chapter, lesson);
        if(answer) {
            System.out.println("IN UPDATEQ "+ ref);
            ref.child("data/c" + chapter + "/l" + lesson + "/q" + question).setValue("1");
            lessonMap.put("q" + question, "1");
        } else{
            ref.child("data/c" + chapter + "/l" + lesson + "/q" + question).setValue("-1");
            lessonMap.put("q" + question, "-1");
        }
    }

    public void updateLesson(String chapter, String lesson, boolean answer){
        Map lessonMap = retrieveLessonMap(chapter, lesson);
        if (answer) {
            ref.child("data/c" + chapter + "/l" + lesson + "/complete").setValue("1");
            lessonMap.put("complete", "1");
        } else{
            ref.child("data/c" + chapter + "/l" + lesson + "/complete").setValue("-1");
            lessonMap.put("complete", "-1");
        }


    }
    public void updateChapter(String chapter, boolean answer){
        Map chapterMap = retrieveChapterMap(chapter);
        if (answer) {
            ref.child("data/c" + chapter + "/complete").setValue("1");
            chapterMap.put("complete", "1");
        } else {
            ref.child("data/c" + chapter + "/complete").setValue("-1");
            chapterMap.put("complete", "-1");
        }
    }

    private Map retrieveChapterMap(String chapter){
        try {
            Map chapterMap = (Map) getData().get("c"+chapter);
            return chapterMap;
        }catch(NullPointerException e) {
            System.out.println("Unlisted Chapter");
        }
        return null;
    }

    private Map retrieveLessonMap(String chapter, String lesson) {
        try {
            Map chapterMap = (Map) getData().get("c" + chapter);
            try {
                Map lessonMap = (Map) chapterMap.get("l" + lesson);
                System.out.println("LESSON MAP "+lessonMap);
                return lessonMap;

            } catch (NullPointerException e) {
                System.out.println("Unlisted Lesson");
            }
        } catch (NullPointerException e) {
            System.out.println("Unlisted Chapter");
        }
        System.out.println("RETURNING NULL");
        return null;
    }

    public int retrieveQuestion(String chapter, String lesson, String question){
        Map lessonMap = retrieveLessonMap(chapter, lesson);
        try{
            System.out.println(lessonMap.get("q" + question));
            return Integer.valueOf((String) lessonMap.get("q"+question));
        }catch(NullPointerException e){
            System.out.println("Unlisted Question");
        }
        return 0;
    }
    public int retrieveLesson(String chapter, String lesson){
        Map lessonMap = retrieveLessonMap(chapter, lesson);
        if (lessonMap != null){
            return Integer.valueOf((String) lessonMap.get("complete"));
        }
        return 0;
    }

    public int retrieveChapter(String chapter) {
        Map chapterMap = retrieveChapterMap(chapter);
        if (chapterMap != null) {
            return Integer.valueOf((String) chapterMap.get("complete"));
        }
        return 0;
    }

}
