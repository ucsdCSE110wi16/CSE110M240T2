package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by devinhickey on 1/26/16.
 */
public class SplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);

                } catch (InterruptedException e) {
                    System.out.println("Error When Running Thread");

                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        };
        timerThread.start();

    }

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}
