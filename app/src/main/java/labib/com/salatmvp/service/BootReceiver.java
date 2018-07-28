package labib.com.salatmvp.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import labib.com.salatmvp.App;
import labib.com.salatmvp.data.DataManager;

public class BootReceiver extends BroadcastReceiver {

    @Inject
    DataManager dataManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        App.getApplicationComponent().inject(this);

        boolean isRunning = getDataManager().retrieveStatus();
        if (isRunning) {
            getDataManager().startAlarm(alarmPendingIntent(context));
        }
    }


    public PendingIntent alarmPendingIntent(Context context) {
        Intent alarmIntent = new Intent(context, AlarmReciever.class);
        return PendingIntent.getBroadcast
                (context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    public DataManager getDataManager() {
        return dataManager;
    }
}
