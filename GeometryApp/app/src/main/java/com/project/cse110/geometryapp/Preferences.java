package com.project.cse110.geometryapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by Migal on 2/29/2016.
 */
public class Preferences {
    private Context context;

    public Preferences(Context context){this.context = context;}

    public void storeUserInfo(final User user){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = user_info.edit();
        editor.putString("uid", user.getUid());
        editor.putString("email", user.getEmail());
        editor.putString("ref", user.getRef().toString());
        editor.apply();
        user.getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getValue(User.class);
                System.out.println("HERE" + user.data);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public String retrieveUIDInfo(){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        return user_info.getString("uid", "");
    }

    public String retrieveEmailInfo(){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        return user_info.getString("email", "");
    }

    public Firebase retrieveRefInfo(){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        String ref = user_info.getString("ref", "");
        return new Firebase(ref);
    }

    public User retrieveUserInfo(){
        final User user =  new User(retrieveRefInfo());
        user.getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user.data = (Map) dataSnapshot.child("data").getValue();
                System.out.println("HERE"+user.data);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return user;
    }

    public boolean checkForUserInfo(){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        return user_info.contains("uid");
    }

    public void clearUserInfo(){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = user_info.edit();
        editor.clear();
        editor.apply();
    }
}
