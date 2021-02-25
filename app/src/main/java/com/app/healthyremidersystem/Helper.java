package com.app.healthyremidersystem;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.healthyremidersystem.Constants;

public class Helper {

    private Context context;

    public Helper(Context context) {
        this.context = context;
    }

    public void saveUserIdLocally(String userId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.USER_ID, userId);
        editor.apply();
    }

    public void removeUserId() {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.USER_ID, null);
        editor.apply();
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.USER_ID, null);
    }

    public int[] splitTimeIntoHourAndMinute(String time) {
        String[] timeSplitted = time.split(":");
        int hour = Integer.parseInt(timeSplitted[0]);
        int minute = Integer.parseInt(timeSplitted[1]);

        int[] hourMinutes = new int[2];
        hourMinutes[0] = hour;
        hourMinutes[1] = minute;
        return hourMinutes;
    }
}
