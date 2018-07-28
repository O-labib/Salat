package labib.com.salatmvp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import labib.com.salatmvp.Logy;


public class AlarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PrayerService.start(context);
    }

}
