package com.project.cse110.geometryapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firebase.client.Firebase;

/**
 * Created by Migal on 2/29/2016.
 */
public class Preferences {

    public Preferences(){}

    public void storeUserInfo(User user, Context context){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = user_info.edit();
        editor.putString("uid", user.getUid());
        editor.putString("email", user.getEmail());
        editor.putString("ref", user.getRef().toString());
        editor.apply();
    }

    public String retrieveUIDInfo(Context context){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        return user_info.getString("uid", "");
    }

    public String retrieveEmailInfo(Context context){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        return user_info.getString("email", "");
    }

    public Firebase retrieveRefInfo(Context context){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        String ref = user_info.getString("ref", "");
        return new Firebase(ref);
    }

    public User retrieveUserInfo(Context context){
        return new User(retrieveUIDInfo(context), retrieveEmailInfo(context), retrieveRefInfo(context));
    }

    public boolean checkForUserInfo(Context context){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        return user_info.contains("uid");
    }

    public void clearUserInfo(Context context){
        SharedPreferences user_info = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = user_info.edit();
        editor.clear();
        editor.apply();
    }
}
