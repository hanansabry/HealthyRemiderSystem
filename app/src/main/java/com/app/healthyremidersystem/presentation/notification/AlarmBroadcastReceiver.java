package com.app.healthyremidersystem.presentation.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.app.healthyremidersystem.Constants;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ALARM_STEPS", "received pending intent in the broadcast reciever to create the notification");
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            startRescheduleAlarmsService(context);
        } else {
            startAlarmService(context, intent);
        }
    }

    private void startAlarmService(Context context, Intent intent) {
        Intent intentService = new Intent(context, AlarmService.class);
        intentService.putExtra(Constants.ALERT_TYPE_ID, intent.getIntExtra(Constants.ALERT_TYPE_ID, 0));
        intentService.putExtra(Constants.ALERT_NAME, intent.getStringExtra(Constants.ALERT_NAME));
        intentService.putExtra(Constants.TIME, intent.getStringExtra(Constants.TIME));
        intentService.putExtra(Constants.POSITION, intent.getIntExtra(Constants.POSITION, 0));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

    private void startRescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, RescheduleAlarmsService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }
}
