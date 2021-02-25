package com.app.healthyremidersystem.presentation.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.FullScreenAlarmActivity;
import com.app.healthyremidersystem.presentation.NotificationActionReceiver;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();
    private static final String ACTION_IGNORE = "com.app.healthremidersystem.ignore_action";
    private static final String ACTION_DONE = "com.app.healthremidersystem.done_action";
    private static final String DEFAULT_CHANNEL_ID = "default";
//    public static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotificationManager;
    private int medicineId;
    private String medicineName;
    private String time;
    private int position;
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: " + intent.getStringExtra(Constants.ALERT_NAME));

        this.context = context;
        if (intent.getExtras() != null) {
            medicineId = intent.getIntExtra(Constants.ALERT_ID, 0);
            medicineName = intent.getStringExtra(Constants.ALERT_NAME);
            time = intent.getStringExtra(Constants.TIME);
            position = intent.getIntExtra(Constants.POSITION, 0);
        }

        PendingIntent contentPendingIntent = createNotificationPendingIntent();
        PendingIntent doneActionPendingIntent = getDoneActionPendingIntent();
        PendingIntent ignoreActionPendingIntent = getIgnoreActionPendingIntent();

        createNotificationChannel();
        deliverNotification(medicineName,
                "It's time to take medicine " + time,
                contentPendingIntent,
                doneActionPendingIntent,
                ignoreActionPendingIntent);
    }

    public void createNotificationChannel() {
        // Create a notification manager object
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

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

    private void deliverNotification(String title, String content,
                                     PendingIntent contentPendingIntent,
                                     PendingIntent doneAction,
                                     PendingIntent ignoreAction) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setFullScreenIntent(contentPendingIntent, true)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .addAction(R.drawable.ic_alarm_icon, "Done", doneAction)
                .addAction(R.drawable.remove_icon, "Ignore", ignoreAction);

        mNotificationManager.notify(medicineId, builder.build());
    }

    private PendingIntent createNotificationPendingIntent() {
        Intent contentIntent = new Intent(context, FullScreenAlarmActivity.class);
        contentIntent.putExtra(Constants.ALERT_ID, medicineId);
        contentIntent.putExtra(Constants.POSITION, position);
        contentIntent.putExtra(Constants.TIME, time);

        return PendingIntent.getActivity(context, medicineId, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public PendingIntent getDoneActionPendingIntent() {
        Intent doneAction = new Intent(context, NotificationActionReceiver.class);
        doneAction.putExtra(Constants.ALERT_ID, medicineId);
        doneAction.putExtra(Constants.POSITION, position);
        doneAction.putExtra(Constants.DONE, true);
        doneAction.setAction(ACTION_DONE);

        return PendingIntent.getBroadcast(context, medicineId, doneAction, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public PendingIntent getIgnoreActionPendingIntent() {
        Intent ignoreAction = new Intent(context, NotificationActionReceiver.class);
        ignoreAction.putExtra(Constants.ALERT_ID, medicineId);
        ignoreAction.putExtra(Constants.POSITION, position);
        ignoreAction.putExtra(Constants.DONE, false);
        ignoreAction.setAction(ACTION_IGNORE);

        return PendingIntent.getBroadcast(context, medicineId, ignoreAction, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
