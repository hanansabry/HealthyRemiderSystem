package com.app.healthyremidersystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Get all medicine times for this user from firebase and set alarms for it
            String userId = new Helper(context).getUserId();
            if (userId != null) {

            }
        }
    }
}
