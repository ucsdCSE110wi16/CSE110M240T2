package com.project.cse110.geometryapp;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by devinhickey on 2/28/16.
 * Used to return the correct drawables.
 */
public class Drawables {

    int chapterNum;
    int lessonNum;

    public Drawables(int chapter, int lesson) {

        this.chapterNum = chapter;
        this.lessonNum = lesson;

    }




    public ArrayList getPagesImagesArray() {
        ArrayList<Integer> array = new ArrayList<>();

        switch (chapterNum) {

            case 1:
                switch (lessonNum) {

                    case 1:
                        array.add(R.drawable.c1_l1_1);
                        array.add(R.drawable.c1_l1_2);
                        array.add(R.drawable.c1_l1_3);

                        return array;

                    case 2:
                        array.add(R.drawable.c1_l2_1);
                        array.add(R.drawable.c1_l2_2);
                        array.add(R.drawable.c1_l2_3);
                        array.add(R.drawable.c1_l2_4);

                        return array;

                    default:
                        return null;

                }

            case 2:
                switch (lessonNum) {

                    case 1:
                        array.add(R.drawable.c2_l1_1);
                        array.add(R.drawable.c2_l1_2);
                        array.add(R.drawable.c2_l1_3);
                        array.add(R.drawable.c2_l1_4);
                        array.add(R.drawable.c2_l1_5);
                        array.add(R.drawable.c2_l1_6);
                        array.add(R.drawable.c2_l1_7);
                        array.add(R.drawable.c2_l1_8);

                        return array;

                    case 2:
                        array.add(R.drawable.c2_l2_1);
                        array.add(R.drawable.c2_l2_2);
                        array.add(R.drawable.c2_l2_3);
                        array.add(R.drawable.c2_l2_4);

                        return array;

                    case 3:
                        array.add(R.drawable.c2_l3_1);
                        array.add(R.drawable.c2_l3_2);

                        return array;

                    default:
                        return null;

                }

            case 3:
                switch (lessonNum) {

                    case 1:
                        array.add(R.drawable.c3_l1_1);
                        array.add(R.drawable.c3_l1_2);
                        array.add(R.drawable.c3_l1_3);
                        array.add(R.drawable.c3_l1_4);
                        array.add(R.drawable.c3_l1_5);
                        array.add(R.drawable.c3_l1_6);
                        array.add(R.drawable.c3_l1_7);
                        array.add(R.drawable.c3_l1_8);

                        return array;

                    case 2:
                        array.add(R.drawable.c3_l2_1);
                        array.add(R.drawable.c3_l2_2);
                        array.add(R.drawable.c3_l2_3);

                        return array;

                    default:
                        return null;

                }

            case 4:
                switch (lessonNum) {

                    case 1:
                        array.add(R.drawable.c4_l1_1);
                        array.add(R.drawable.c4_l1_2);
                        array.add(R.drawable.c4_l1_3);
                        array.add(R.drawable.c4_l1_4);
                        array.add(R.drawable.c4_l1_5);

                        return array;

                    case 2:
                        array.add(R.drawable.c4_l2_1);
                        array.add(R.drawable.c4_l2_2);


                        return array;

                    case 3:
                        array.add(R.drawable.c4_l3_1);
                        array.add(R.drawable.c4_l3_2);
                        array.add(R.drawable.c4_l3_3);

                        return array;


                } // end lesson 4

            case 5:
                switch(lessonNum) {

                    case 1:
                        array.add(R.drawable.c5_l1_1);
                        array.add(R.drawable.c5_l1_2);

                        return array;


                    case 2:
                        array.add(-1);
                        array.add(R.drawable.c5_l2_2);
                        array.add(R.drawable.c5_l2_3);
                        array.add(R.drawable.c5_l2_4);
                        array.add(R.drawable.c5_l2_5);

                        return array;

                    default:
                        return null;

                }

            default:
                return null;
        }

    }

    /*
        Called to set the image of the question.
        Param: the image view to set the png for.

     */
    public int getQuestionImage(int qNum) {
        System.out.println("Getting Question Image");

        switch (chapterNum) {

            case 1:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {

                            case 1:
                                return R.drawable.c1_l1_q1;
                            case 2:
                                return R.drawable.c1_l1_q2;
                            case 3:
                                return (R.drawable.c1_l1_q3);
                            case 4:
                                return (R.drawable.c1_l1_q4);
                            case 5:
                                return (R.drawable.c1_l1_q5);
                            case 6:
                                return (R.drawable.c1_l1_q6);
                            case 7:
                                return (R.drawable.c1_l1_q7);
                            default:
                                return -1;

                        }

                         // end Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c1_l2_q1);
                            case 2:
                                return (R.drawable.c1_l2_q2);
                            case 3:
                                return (R.drawable.c1_l2_q3);
                            case 4:
                                return (R.drawable.c1_l2_q4);
                            case 5:
                                return (R.drawable.c1_l2_q5);
                            default:
                                return -1;


                        }

                    default:
                        return -1; // end Chapter 1 Lesson 2


                }
                 // end Chapter 1

            case 2:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c2_l1_q1);
                            case 2:
                                return (R.drawable.c2_l1_q2);
                            case 3:
                                return (R.drawable.c2_l1_q3);

                            default:
                                return -1;


                        }

                         // end Chapter 2 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c2_l2_q1);
                            case 2:
                                return (R.drawable.c2_l2_q2);
                            case 3:
                                return (R.drawable.c2_l2_q3);
                            case 4:
                                return (R.drawable.c2_l2_q4);
                            case 5:
                                return (R.drawable.c2_l2_q5);
                            case 6:
                                return (R.drawable.c2_l2_q6);
                            case 7:
                                return (R.drawable.c2_l2_q7);

                            default:
                                return -1; // end Chapter 2 Lesson 2


                        }


                    case 3:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c2_l3_q1);
                            case 2:
                                return (R.drawable.c2_l3_q2);
                            case 3:
                                return (R.drawable.c2_l3_q3);
                            case 4:
                                return (R.drawable.c2_l3_q4);

                            default:
                                return -1;

                        }

                    default:
                        return -1; // end Chapter 2 Lesson 3

                }
                  // end Chapter 2

            case 3:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c3_l1_q1);
                            case 2:
                                return (R.drawable.c3_l1_q2);
                            case 3:
                                return (R.drawable.c3_l1_q3);
                            case 4:
                                return (R.drawable.c3_l1_q4);
                            case 5:
                                return (R.drawable.c3_l1_q5);

                            default:
                                return -1;


                        }

                        // end Chapter 3 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c3_l2_q1);
                            case 2:
                                return (R.drawable.c3_l2_q2);
                            case 3:
                                return (R.drawable.c3_l2_q3);
                            case 4:
                                return (R.drawable.c3_l2_q4);
                            case 5:
                                return (R.drawable.c3_l2_q5);

                            default:
                                return -1;


                        }

                    default:
                        return -1; // end Chapter 3 Lesson 2

                }


            case 4:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c4_l1_q1);
                            case 2:
                                return (R.drawable.c4_l1_q2);
                            case 3:
                                return (R.drawable.c4_l1_q3);
                            case 4:
                                return (R.drawable.c4_l1_q4);
                            case 5:
                                return (R.drawable.c4_l1_q5);

                            default:
                                return -1;


                        }

                         // end Chapter 4 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c4_l2_q1);
                            case 2:
                                return (R.drawable.c4_l2_q2);
                            case 3:
                                return (R.drawable.c4_l2_q3);
                            case 4:
                                return (R.drawable.c4_l2_q4);

                            default:
                                return -1;


                        }
                          // end Chapter 4 Lesson 2

                    case 3:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c4_l3_q1);
                            case 2:
                                return (R.drawable.c4_l3_q2);
                            case 3:
                                return (R.drawable.c4_l3_q3);
                            case 4:
                                return (R.drawable.c4_l3_q4);
                            case 5:
                                return (R.drawable.c4_l3_q5);

                            default:
                                return -1;

                        }

                       // end Chapter 4 Lesson 3

                    default:
                        return -1;

                }
                 // end Chapter 4

            case 5:
                switch (lessonNum) {
                    case 1:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c5_l1_q1);
                            case 2:
                                return (R.drawable.c5_l1_q2);
                            case 3:
                                return (R.drawable.c5_l1_q3);

                            default:
                                return -1;


                        }

                          // end Chapter 5 Lesson 1

                    case 2:
                        switch (qNum) {
                            case 1:
                                return (R.drawable.c5_l2_q1);
                            case 2:
                                return (R.drawable.c5_l2_q2);
                            case 3:
                                return (R.drawable.c5_l2_q3);
                            case 4:
                                return (R.drawable.c5_l2_q4);
                            case 5:
                                return (R.drawable.c5_l2_q5);

                            default:
                                return -1;


                        }
                        // end Chapter 5 Lesson 2

                    default:
                        return -1;

                }

            default:

                return-1;


        }

    }


}
