package com.app.healthyremidersystem.presentation.notification;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.R;

import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    private static final String TAG = AlarmService.class.getSimpleName();

    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    private int alertTypeId;
    private String alertName;
    private String time;
    private int position;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        mediaPlayer.setLooping(true);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ALARM_STEPS", "start alarm service");
        if (intent.getExtras() != null) {
            alertTypeId = intent.getIntExtra(Constants.ALERT_TYPE_ID, 0);
            alertName = intent.getStringExtra(Constants.ALERT_NAME);
            time = intent.getStringExtra(Constants.TIME);
            position = intent.getIntExtra(Constants.POSITION, 0);
        }

        NotificationCreator notificationCreator = new NotificationCreator(this, alertTypeId, alertName, time, position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(alertTypeId, notificationCreator.createNotification());
        } else {
            notificationCreator.createNotification();
        }

        mediaPlayer.start();

        long[] pattern = { 0, 100, 1000};
        vibrator.vibrate(pattern, 0);

        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        vibrator.cancel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
