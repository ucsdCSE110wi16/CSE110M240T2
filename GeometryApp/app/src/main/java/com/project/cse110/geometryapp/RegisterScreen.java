package com.project.cse110.geometryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
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

        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.out.println("Inside OK");

                finish();
            }
        });

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
                        //ref.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            User user= new User(result.get("uid").toString(), emailID, ref.child("users/"+result.get("uid")).getRef());
                            ref.child("users/"+user.getUid()).setValue(user);
                            //System.out.println(user.getData());
                            System.out.println("Successfully created user account with uid: " + result.get("uid"));
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                            System.out.println("There was an error reg istering your account: "+firebaseError.getMessage());
                        }
                    });
                } else {
                    System.out.println("Error: Passwords Do Not Match");

                    dialogBuilder.setMessage("Passwords Do Not Match");
                    dialog = dialogBuilder.create();
                    dialog.show();

                }
            }

        });


    }
}
