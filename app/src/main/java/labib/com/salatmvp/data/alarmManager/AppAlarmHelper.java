package labib.com.salatmvp.data.alarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

import labib.com.salatmvp.utils.AppUtils;

public class AppAlarmHelper implements AlarmHelper {

    private AlarmManager alarmManager;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private long weekInMills = 604800000;


    public AppAlarmHelper(AlarmManager alarmManager, Calendar startCalendar, Calendar endCalendar) {
        this.alarmManager = alarmManager;
        this.startCalendar = startCalendar;
        this.endCalendar = endCalendar;
    }


    @Override
    public void setStartCalendar(int d, int h, int m) {
        startCalendar = AppUtils.setToNext(startCalendar, d);
        startCalendar.set(Calendar.HOUR_OF_DAY, h);
        startCalendar.set(Calendar.MINUTE, m);
//        Logy.i(AppUtils.calendarToReadableDate(startCalendar));
    }

    @Override
    public void setEndCalendar(int h, int m) {
        endCalendar = AppUtils.setToNext(endCalendar, Calendar.FRIDAY);
        endCalendar.set(Calendar.HOUR_OF_DAY, h);
        endCalendar.set(Calendar.MINUTE, m);
//        Logy.i(AppUtils.calendarToReadableDate(endCalendar));
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
    public void startAlarm(PendingIntent pendingIntent) {

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);
    }

    @Override
    public void stopAlarm(PendingIntent pendingIntent) {
        alarmManager.cancel(pendingIntent);

    }
}
