package com.luffy.openthedoor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreference {

    public static final String KEY_LOCATION = "location";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    private static SharedPreferences sSharedPreferences;

    private String mLocation;
    private String mUsername;
    private String mPassword;

    MyPreference(Context context) {
        if (sSharedPreferences == null) {
            sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        }

        //change the default value here
        mLocation = sSharedPreferences.getString(KEY_LOCATION, "wangejay.ddns.net");
        mUsername = sSharedPreferences.getString(KEY_USERNAME, "");
        mPassword = sSharedPreferences.getString(KEY_PASSWORD, "");
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    void save() {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(KEY_LOCATION, mLocation);
        editor.putString(KEY_USERNAME, mUsername);
        editor.putString(KEY_PASSWORD, mPassword);
        editor.commit();
    }
}
