package labib.com.salatmvp.data.alarmManager;

import android.app.PendingIntent;

public interface AlarmHelper {

    void setStartCalendar(int d, int h, int m);

    void setEndCalendar(int h, int m);

    long getDeltaMills();

    long getEndInMills();

    long getStartInMills();

    void startAlarm();

    void stopAlarm();

}
