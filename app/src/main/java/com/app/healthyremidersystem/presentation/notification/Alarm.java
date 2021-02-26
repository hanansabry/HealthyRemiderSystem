package com.app.healthyremidersystem.presentation.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.app.healthyremidersystem.Constants;

import java.util.Calendar;

public class Alarm {
//    private static final long WEEKLY = 7 * 24 * 60 * 60 * 1000;
    private static final long WEEKLY = 5 * 60 * 1000;

    private int position;
    private int alarmId;
    private int alarmTypeId;
    private int year, month, day, hour, minutes;
    private String alertName;
    private boolean started, repeating;

    public Alarm() {
    }

    public Alarm(int position, int year, int month, int day, int hour, int minutes, String alertName, int alarmTypeId, int alarmId, boolean repeating) {
        this.position = position;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
        this.alertName = alertName;
        this.alarmId = alarmId;
        this.alarmTypeId = alarmTypeId;
        this.repeating = repeating;
    }

    public void schedule(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);

        intent.putExtra(Constants.ALERT_TYPE_ID, alarmTypeId);
        intent.putExtra(Constants.ALERT_NAME, alertName);
        intent.putExtra(Constants.TIME, hour + ":" + minutes);
        intent.putExtra(Constants.POSITION, position);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (repeating) {
            calendar.set(Calendar.DAY_OF_WEEK, day);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);

            if(calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DAY_OF_MONTH, 7);
            }

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), WEEKLY, alarmIntent);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, hour-2);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MINUTE, minutes);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
            }
        }
        Log.i("TIME_MILLIS", "setAlarm: " + alertName + ", " + position + "," + calendar.getTimeInMillis());
        started = true;
        Log.i("ALARM_STEPS", "schedule alarm in alarm activity and start it");
    }

    public void cancelAlarm(Context context, int alarmId) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
        if (alarmPendingIntent != null) {
            alarmManager.cancel(alarmPendingIntent);
        }
        this.started = false;

        Toast.makeText(context, "Alarm cancelled", Toast.LENGTH_SHORT).show();
    }
}
