package com.project.cse110.geometryapp;

/**
 * Created by Kedar on 2/12/16.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//package com.firebase.samples.logindemo;


//import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;

public class LoginScreen extends Activity {

    EditText editTextEmail;
    EditText editTextPassword;
    AlertDialog dialog;
    AlertDialog.Builder dialogBuilder;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        Firebase.setAndroidContext(this);
        final Context context = this;
        setContentView(R.layout.login_screen);

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

        //titleBar.setText("Geometry App");

        // Create the dialog box
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.out.println("Inside OK");

            }
        });



        editTextEmail = (EditText) findViewById(R.id.etUsername);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        // Set the login button onClick
        Button bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Login Button clicked");
                String emailID = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Check for internet connection
                boolean networkConnected;
                boolean internetConnected;
                networkConnected = isNetworkAvailable();
                internetConnected = isOnline();

                if (networkConnected == false && internetConnected == false ) {
                    dialogBuilder.setMessage("No internet connection");
                    dialog = dialogBuilder.create();
                    dialog.show();
                    return;
                }
                System.out.println("Past network check");

                final Firebase ref = new Firebase("https://cse110geometry.firebaseio.com");


                ref.authWithPassword(emailID, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        User user = new User(authData.getUid(), authData.getProviderData().get("email").toString(), ref.child("/users/" + authData.getUid()).getRef());
                        Preferences user_info = new Preferences(getApplicationContext());
                        user_info.storeUserInfo(user);
                        Intent newIntent = new Intent(LoginScreen.this, Main.class);
                        startActivity(newIntent);
                        editTextEmail.setText("");
                        editTextPassword.setText("");

                    }

                    // Called when FireBase cannot authenticate
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        System.out.println("Error logging in. Please try again");
                        dialogBuilder.setMessage("Invalid Email/Password");
                        dialog = dialogBuilder.create();
                        dialog.show();
                    }
                });


            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(LoginScreen.this, RegisterScreen.class);


                startActivity(newIntent);
                editTextEmail.setText("");
                editTextPassword.setText("");
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


}
