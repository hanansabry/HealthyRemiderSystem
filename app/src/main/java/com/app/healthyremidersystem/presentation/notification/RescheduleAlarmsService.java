package com.app.healthyremidersystem.presentation.notification;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.app.healthyremidersystem.Constants;
import com.app.healthyremidersystem.Helper;
import com.app.healthyremidersystem.domain.RescheduleAlarmsRepositoryImpl;
import com.app.healthyremidersystem.model.Medicine;
import com.app.healthyremidersystem.presentation.viewmodels.RescheduleAlarmsViewModel;

import java.util.List;

import androidx.core.app.NotificationCompat;

public class RescheduleAlarmsService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(0, new NotificationCompat.Builder(this, Constants.DEFAULT_CHANNEL_ID).build());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(0, new NotificationCompat.Builder(this, Constants.DEFAULT_CHANNEL_ID).build());
        }
        RescheduleAlarmsViewModel rescheduleAlarmsViewModel = new RescheduleAlarmsViewModel(new Helper(this).getUserId());
        rescheduleAlarmsViewModel.rescheduleAlarms(medicines -> {
            for (Medicine medicine : medicines) {
                medicine.setMedicineAlarms(this);
            }
        });
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
