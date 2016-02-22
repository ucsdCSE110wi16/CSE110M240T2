package com.project.cse110.geometryapp;

import android.app.Application;
import android.widget.EditText;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import com.firebase.client.Firebase;
import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;

package com.firebase.samples.logindemo;

//import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;
/**
 * Created by Kedar on 2/21/16.
 */
public class RegisterScreen extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        private EditText editTextEmail;
        private EditText editTextPassword;
        //private TextView textViewEmail;

        editTextEmail = (EditText) findViewById(R.id.regUsername);
        editTextPassword = (EditText) findViewById(R.id.regPassword);
        String emailID = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        Firebase ref = new Firebase("https://cse110geometry.firebaseio.com");
        ref.createUser(emailID, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            //ref.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
                System.out.println("There was an error registering your account");
            }
        });
    }
}
