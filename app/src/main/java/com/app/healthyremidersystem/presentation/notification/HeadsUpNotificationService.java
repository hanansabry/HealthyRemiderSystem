package com.app.healthyremidersystem.presentation.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.FullScreenAlarmActivity;
import com.app.healthyremidersystem.presentation.NotificationActionReceiver;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class HeadsUpNotificationService extends Service {
    private static final String ACTION_IGNORE = "com.app.healthremidersystem.ignore_action";
    private static final String ACTION_DONE = "com.app.healthremidersystem.done_action";
    private static final String DEFAULT_CHANNEL_ID = "default";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotificationManager;
    private String medicineId;
    private String medicineName;
    private String time;
    private int position;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null && intent.getExtras() != null) {
            medicineId = intent.getStringExtra(Constants.ALERT_ID);
            medicineName = intent.getStringExtra(Constants.ALERT_NAME);
            time = intent.getStringExtra(Constants.TIME);
            position = intent.getIntExtra(Constants.POSITION, -1);
        }

        Intent doneAction = new Intent(this, NotificationActionReceiver.class);
        doneAction.putExtra(Constants.ALERT_ID, medicineId);
        doneAction.putExtra(Constants.POSITION, position);
        doneAction.putExtra(Constants.DONE, true);
        doneAction.setAction(ACTION_DONE);


        Intent ignoreAction = new Intent(this, NotificationActionReceiver.class);
        ignoreAction.putExtra(Constants.ALERT_ID, medicineId);
        ignoreAction.putExtra(Constants.POSITION, position);
        ignoreAction.putExtra(Constants.DONE, false);
        ignoreAction.setAction(ACTION_IGNORE);

        PendingIntent donePendingIntent = PendingIntent.getBroadcast(this, 0, doneAction, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent ignorePendingIntent = PendingIntent.getBroadcast(this, 0, ignoreAction, PendingIntent.FLAG_CANCEL_CURRENT);

        createNotificationChannel();
        deliverNotification(createNotificationPendingIntent(), medicineName, "It's time to take medicine " + time);

        return START_STICKY;
    }

    public void createNotificationChannel() {
        // Create a notification manager object
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(DEFAULT_CHANNEL_ID,
                    "Alarm notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification alarm for every time of medicine");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void deliverNotification(PendingIntent contentPendingIntent, String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setFullScreenIntent(contentPendingIntent, true)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private PendingIntent createNotificationPendingIntent() {
        Intent contentIntent = new Intent(this, FullScreenAlarmActivity.class);
        contentIntent.putExtra(Constants.ALERT_ID, medicineId);
        contentIntent.putExtra(Constants.POSITION, position);
        contentIntent.putExtra(Constants.TIME, time);
//        contentIntent.putExtra(Constants.DONE, true);

        return PendingIntent.getActivity(this, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
