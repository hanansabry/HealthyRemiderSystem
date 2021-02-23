package com.app.healthyremidersystem;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {

    public static final String SHARED_PREF = "main";
    public static final String USER_ID = "user_id";

    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID, null);
    }
}
