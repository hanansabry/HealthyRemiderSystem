package com.app.healthyremidersystem.presentation.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;
import com.app.healthyremidersystem.presentation.FullScreenAlarmActivity;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationCreator {
    private static final String ACTION_IGNORE = "com.app.healthremidersystem.ignore_action";
    private static final String ACTION_DONE = "com.app.healthremidersystem.done_action";

    private int alertTypeId;
    private String alertName;
    private String time;
    private int position;
    private Context context;

    public NotificationCreator(Context context, int alertTypeId, String alertName, String time, int position) {
        this.context = context;
        this.alertTypeId = alertTypeId;
        this.alertName = alertName;
        this.time = time;
        this.position = position;
    }

    public Notification createNotification() {
        Log.i("ALARM_STEPS", "create the notification");

        PendingIntent contentPendingIntent = createNotificationPendingIntent();
        PendingIntent doneActionPendingIntent = getDoneActionPendingIntent();
        PendingIntent ignoreActionPendingIntent = getIgnoreActionPendingIntent();

        return deliverNotification(alertName,
                "It's time to take medicine " + time,
                contentPendingIntent,
                doneActionPendingIntent,
                ignoreActionPendingIntent);
    }

    private Notification deliverNotification(String title, String content,
                                     PendingIntent contentPendingIntent,
                                     PendingIntent doneAction,
                                     PendingIntent ignoreAction) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.DEFAULT_CHANNEL_ID)
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

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(alertTypeId, builder.build());
        return builder.build();
    }

    private PendingIntent createNotificationPendingIntent() {
        Intent contentIntent = new Intent(context, FullScreenAlarmActivity.class);
        contentIntent.putExtra(Constants.ALERT_TYPE_ID, alertTypeId);
        contentIntent.putExtra(Constants.POSITION, position);
        contentIntent.putExtra(Constants.TIME, time);

        return PendingIntent.getActivity(context, alertTypeId, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public PendingIntent getDoneActionPendingIntent() {
        Intent doneAction = new Intent(context, NotificationActionReceiver.class);
        doneAction.putExtra(Constants.ALERT_TYPE_ID, alertTypeId);
        doneAction.putExtra(Constants.POSITION, position);
        doneAction.putExtra(Constants.DONE, true);
        doneAction.setAction(ACTION_DONE);

        return PendingIntent.getBroadcast(context, alertTypeId, doneAction, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public PendingIntent getIgnoreActionPendingIntent() {
        Intent ignoreAction = new Intent(context, NotificationActionReceiver.class);
        ignoreAction.putExtra(Constants.ALERT_TYPE_ID, alertTypeId);
        ignoreAction.putExtra(Constants.POSITION, position);
        ignoreAction.putExtra(Constants.DONE, false);
        ignoreAction.setAction(ACTION_IGNORE);

        return PendingIntent.getBroadcast(context, alertTypeId, ignoreAction, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
