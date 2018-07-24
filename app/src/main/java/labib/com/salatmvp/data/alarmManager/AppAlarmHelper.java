package labib.com.salatmvp.data.alarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import labib.com.salatmvp.service.AlarmReciever;
import labib.com.salatmvp.service.PrayerService;
import labib.com.salatmvp.utils.AppUtils;

public class AppAlarmHelper implements AlarmHelper {

    private AlarmManager alarmManager;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private long weekInMills = 604800000;


    private Context context;

    public AppAlarmHelper(AlarmManager alarmManager, Calendar startCalendar, Calendar endCalendar, Context context) {
        this.alarmManager = alarmManager;
        this.startCalendar = startCalendar;
        this.endCalendar = endCalendar;
        this.context = context;
    }


    @Override
    public void setStartCalendar(int d, int h, int m) {
        startCalendar = AppUtils.setToNext(startCalendar, d);
        startCalendar.set(Calendar.HOUR_OF_DAY, h);
        startCalendar.set(Calendar.MINUTE, m);
    }

    @Override
    public void setEndCalendar(int h, int m) {
        endCalendar = AppUtils.setToNext(endCalendar, Calendar.FRIDAY);
        endCalendar.set(Calendar.HOUR_OF_DAY, h);
        endCalendar.set(Calendar.MINUTE, m);
    }

    @Override
    public long getDeltaMills() {
        return endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
    }

    @Override
    public long getEndInMills() {
        return endCalendar.getTimeInMillis();
    }

    @Override
    public long getStartInMills() {
        return startCalendar.getTimeInMillis();
    }

    @Override
    public void startAlarm() {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getStartInMills(), weekInMills, alarmPendingIntent());
    }

    @Override
    public void stopAlarm() {
        alarmManager.cancel(alarmPendingIntent());
        PrayerService.stop(context);
    }


    private PendingIntent alarmPendingIntent() {
        Intent alarmIntent = new Intent(context, AlarmReciever.class);
        return PendingIntent.getBroadcast
                (context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
