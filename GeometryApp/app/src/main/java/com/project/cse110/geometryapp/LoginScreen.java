package com.project.cse110.geometryapp;

/**
 * Created by Kedar on 2/12/16.
 */

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

//private EditText editTextEmail;
//private TextView textViewEmail;

public class LoginScreen extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        private EditText editTextEmail;
        private EditText editTextPassword;
        //private TextView textViewEmail;

        editTextEmail = (EditText) findViewById(R.id.etUsername);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        String emailID = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        //textViewEmail = (TextView) findViewById(R.id.etUsername);
        //textViewEmail.setText(editTextEmail.getText().toString());
        //editTextEmail.setOnEditorActionListener(new TextView.onEditorActionListener());

        Firebase ref = new Firebase("https://cse110geometry.firebaseio.com");
        //Query queryRef = ref.orderByChild("email").equalTo(R.id.etUsername);

        ref.authWithPassword(emailID, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
                System.out.println("Error logging in. Please try again");
            }
        });




    /*@Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        FacebookSdk.sdkInitialize(this);
    }*/
}
