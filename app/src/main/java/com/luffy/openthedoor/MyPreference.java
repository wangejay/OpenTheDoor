package com.luffy.openthedoor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreference {

    public static final String KEY_LOCATION = "location";
    private static SharedPreferences sSharedPreferences;

    private String mLocation;

    MyPreference(Context context) {
        if (sSharedPreferences == null) {
            sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        }
        //change the default value here
        mLocation = sSharedPreferences.getString(KEY_LOCATION, "wangejay.ddns.net");
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    void save() {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(KEY_LOCATION, mLocation);
        editor.commit();
    }
}
