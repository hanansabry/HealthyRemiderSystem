package com.app.healthyremidersystem.presentation.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.app.healthyremidersystem.Constants;

import java.util.Calendar;

public class AlarmController {

    private static final long IN_WEEK = 7 * 24 * 60 * 60 * 1000;
    private Context context;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public AlarmController(Context context) {
        this.context = context;
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void setRepeatingAlarm(int position, int day, int hour, int minutes, String alertName, int alertId, int alertIntentId) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(Constants.ALERT_ID, alertId);
        intent.putExtra(Constants.ALERT_NAME, alertName);
        intent.putExtra(Constants.TIME, hour + ":" + minutes);
        intent.putExtra(Constants.POSITION, position);
        alarmIntent = PendingIntent.getBroadcast(context, alertIntentId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), IN_WEEK, alarmIntent);
//        } else {
//            alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
//        }
    }

    public void setAlarm(int day, int month, int year, int hour, int minutes, String alertName, int alertId) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(Constants.ALERT_ID, alertId);
        intent.putExtra(Constants.ALERT_NAME, alertName);
        intent.putExtra(Constants.TIME, hour + ":" + minutes);
        intent.putExtra(Constants.POSITION, 0);
        alarmIntent = PendingIntent.getBroadcast(context, alertId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour-2);
        calendar.set(Calendar.MINUTE, minutes);

        Log.i("TIME_MILLIS", "setAlarm: " + calendar.getTimeInMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmMgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
        } else {
            alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
        }
    }
}
