package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by devinhickey on 2/6/16.
 */
public class LessonDescription extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        System.out.println("Inside LessonDescription onCreate");
        setContentView(R.layout.lesson_description);

        String body = getIntent().getExtras().getString("body");
        TextView text = (TextView) findViewById(R.id.textView);

        text.setText(body);

        Button skipButton = (Button) findViewById(R.id.skip);
        skipButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent questionIntent = new Intent(LessonDescription.this, QuestionList.class);

                /*
                int question = R.drawable.c1_l1_q1;
                questionIntent.putExtra("question", question);

                String response1 = "A";
                questionIntent.putExtra("first", response1);

                String response2 = "B";
                questionIntent.putExtra("second", response2);

                String response3 = "C";
                questionIntent.putExtra("third", response3);

                int answer = 1;
                questionIntent.putExtra("answer", answer);

                int qNum = 1;
                questionIntent.putExtra("qNum", qNum);
*/

                startActivity(questionIntent);

            }

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();

    }
}
