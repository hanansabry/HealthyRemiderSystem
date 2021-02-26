package com.app.healthyremidersystem.presentation.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.domain.MedicineRepositoryImpl;

public class NotificationActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int medicineId = intent.getIntExtra(Constants.ALERT_TYPE_ID, -1);
        int timePosition = intent.getIntExtra(Constants.POSITION, -1);
        boolean done = intent.getBooleanExtra(Constants.DONE, false);

        if (done) {
            MedicineRepository medicineRepository = new MedicineRepositoryImpl();
            medicineRepository.updateScheduledTimeStatus(new Helper(context).getUserId(), String.valueOf(medicineId), timePosition, true);
        }
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
        //stop the service
        Intent intentService = new Intent(context, AlarmService.class);
        context.stopService(intentService);
    }
}
