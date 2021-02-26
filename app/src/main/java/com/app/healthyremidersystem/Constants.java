package com.app.healthyremidersystem;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {

    public static final String SHARED_PREF = "main";
    public static final String USER_ID = "user_id";
    public static final String ALERT_NAME = "medicine_name";
    public static final String TIME = "time";
    public static final String POSITION = "position";
    public static final String ALERT_TYPE_ID = "medicine_id";
    public static final String DONE = "done";
    public static final String DEFAULT_CHANNEL_ID = "default";

    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID, null);
    }
}
