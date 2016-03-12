package com.project.cse110.geometryapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import com.firebase.client.Firebase;
import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;


//import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;

import java.util.Map;

/**
 * Created by Kedar on 2/21/16.
 */
public class RegisterScreen extends Activity {
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editConfirmPassword;
    Button registerButton;
    AlertDialog dialog;
    AlertDialog.Builder dialogBuilder;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        Firebase.setAndroidContext(this);

        setContentView(R.layout.register_screen);

        // Start ActionBar
        ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflater = LayoutInflater.from(this);

        LinearLayout abLayout = (LinearLayout) inflater.inflate(R.layout.titlebarlayout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.LEFT);

        ab.setCustomView(abLayout, params);
        ab.setDisplayHomeAsUpEnabled(false);


        // Make the buttons on the ab disappear
        TextView myProgress = (TextView) abLayout.findViewById(R.id.progress);
        Button homeButton = (Button) abLayout.findViewById(R.id.home);
        TextView titleBar = (TextView) abLayout.findViewById(R.id.actionBarTitle);

        myProgress.setVisibility(View.INVISIBLE);
        homeButton.setVisibility(View.INVISIBLE);


        // Create the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);


        editTextEmail = (EditText) findViewById(R.id.regUsername);
        editTextPassword = (EditText) findViewById(R.id.regPassword);
        editConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        registerButton = (Button) findViewById(R.id.bRegister);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailID = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editConfirmPassword.getText().toString();

                if (password.equals(confirmPassword)) {
                    System.out.println("Passwords match");
                    final Firebase ref = new Firebase("https://cse110geometry.firebaseio.com");
                    ref.createUser(emailID, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {

                            User user = new User(result.get("uid").toString(), emailID, ref.child("users/" + result.get("uid")).getRef());
                            Preferences user_info = new Preferences(getApplicationContext());
                            user_info.storeUserInfo(user);
                            ref.child("users/" + user.getUid()).setValue(user);
                            dialogBuilder.setMessage("Account successfully created!");
                            dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    System.out.println("Inside OK");
                                    Intent newIntent = new Intent(RegisterScreen.this, Main.class);
                                    startActivity(newIntent);
                                }
                            });

                            dialog = dialogBuilder.create();
                            dialog.show();
                            System.out.println("Successfully created user account with uid: " + result.get("uid"));

                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                            System.out.println("There was an error registering your account: " + firebaseError.getMessage());

                            if (firebaseError.getMessage().equals("The specified email address is invalid.")) {
                                dialogBuilder.setMessage("Invalid Email Address");
                            } else if (firebaseError.getMessage().equals("The specified email address is already in use.")) {
                                dialogBuilder.setMessage("The Email Address is already in use");
                            } else {
                                boolean networkConnected;
                                boolean internetConnected;
                                networkConnected = isNetworkAvailable();
                                internetConnected = isOnline();
                                if (networkConnected == false && internetConnected == false ) {
                                    dialogBuilder.setMessage("Please Check Your Internet Connection");
                                } else {
                                    dialogBuilder.setMessage("Error Connecting to Database");

                                }

                            }

                            dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    System.out.println("Inside OK");

                                }

                            });

                            dialog = dialogBuilder.create();
                            dialog.show();

                        }
                    });
                } else {
                    System.out.println("Error: Passwords Do Not Match");

                    dialogBuilder.setMessage("Passwords Do Not Match");
                    dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            System.out.println("Inside OK");

                        }
                    });
                    dialog = dialogBuilder.create();
                    dialog.show();

                }
            }

        });

    }

    private boolean isNetworkAvailable() {
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.out.println("Inside OK");

            }
        });
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean connected;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent newIntent = new Intent(RegisterScreen.this, LoginScreen.class);
        startActivity(newIntent);

    }
}
