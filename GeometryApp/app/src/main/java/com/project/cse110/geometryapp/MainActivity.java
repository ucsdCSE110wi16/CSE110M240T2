package com.project.cse110.geometryapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editTextEmailLogin;
        EditText editTextEmailRegister;
        EditText editTextPasswordLogin;
        EditText editTextPasswordRegister;

        editTextEmailLogin = (EditText) findViewById(R.id.etUsername);
        editTextEmailRegister = (EditText) findViewById(R.id.regUsername);
        editTextPasswordLogin = (EditText) findViewById(R.id.etPassword);
        editTextPasswordRegister = (EditText) findViewById(R.id.regPassword);

        String emailIDLogin = editTextEmailLogin.getText().toString();
        String emailIDRegister = editTextEmailRegister.getText().toString();
        String passwordLogin = editTextPasswordLogin.getText().toString();
        String passwordRegister = editTextPasswordRegister.getText().toString();

        Firebase ref = new Firebase("https://cse110geometry.firebaseio.com");

        ref.authWithPassword(emailIDLogin, passwordLogin, new Firebase.AuthResultHandler() {
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

        Firebase ref2 = new Firebase("https://cse110geometry.firebaseio.com");
        ref2.createUser(emailIDRegister, passwordRegister, new Firebase.ValueResultHandler<Map<String, Object>>() {
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


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        Button first = (Button) findViewById(R.id.firstButton);
//        first.setOnClickListener(new View.OnClickListener()){
//            @Override
//                    public void onClick(View v){
//
//                first
//            }

        //};
    }



    public void onClick(View v) {

        switch(v.getId()) {

            //case R.id.firstButton:
              //  System.out.println("First Button Clicked");

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
