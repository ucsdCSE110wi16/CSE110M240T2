package com.project.cse110.geometryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by devinhickey on 1/26/16.
 * Creates a Launch SplashScreen for the App.
 * Displays for 3 seconds before calling the MainActivity
 * Intent which enters the app.
 */
public class SplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the xml layout
        setContentView(R.layout.splash_screen);

        // Create a thread to run
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    // Waits for 3 seconds before executing
                    sleep(3000);

                } catch (InterruptedException e) {
                    System.out.println("Error When Running Thread");

                } finally {
                    // Start the next intent, the MainActivity
                    Intent intent = new Intent(SplashScreen.this, Main.class);
                    startActivity(intent);

                }

            }
        };
        // Call the created Thread
        timerThread.start();

    }

    @Override
    public void onStop() {
        super.onStop();
        // Delete the SplashScreen from the stack
        finish();
    }
}
