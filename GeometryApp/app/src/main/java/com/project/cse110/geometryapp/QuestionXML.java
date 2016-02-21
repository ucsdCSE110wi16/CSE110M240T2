package com.project.cse110.geometryapp;

/**
 * Created by Abhishek on 2/20/16.
 */
public class QuestionXML {
    int questionNumber;
    String questionType;
    String imageName;
    String responses;
    String answers;
    int chapterNumber;
    int lessonNumber;
    String lessonName;

    public QuestionXML() {
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getImageName() {
        return imageName;
    }

    public String getResponses() {
        return responses;
    }

    public String getAnswers() {
        return answers;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public String getLessonName() {
        return lessonName;
    }
}
