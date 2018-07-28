package labib.com.salatmvp.data;

import android.app.PendingIntent;

import labib.com.salatmvp.data.alarmManager.AppAlarmHelper;
import labib.com.salatmvp.data.sharePreferences.AppSharedPrefHelper;


public class AppDataManager implements DataManager {


    private AppSharedPrefHelper appSharedPrefHelper;
    private AppAlarmHelper appAlarmHelper;

    public AppDataManager(AppSharedPrefHelper appSharedPrefHelper, AppAlarmHelper appAlarmHelper) {
        this.appSharedPrefHelper = appSharedPrefHelper;
        this.appAlarmHelper = appAlarmHelper;
    }

    @Override
    public void updateNofPrayers(int nOfPrayers) {
        appSharedPrefHelper.updateNofPrayers(nOfPrayers);
    }

    @Override
    public int retrieveNofPrayers() {
        return appSharedPrefHelper.retrieveNofPrayers();
    }

    @Override
    public void updateActiveNoPrayers(int n) {
        appSharedPrefHelper.updateActiveNoPrayers(n);
    }

    @Override
    public int retrieveActiveNofPrayer() {
        return appSharedPrefHelper.retrieveNofPrayers();
    }

    @Override
    public void updateStartAt(int D, int h, int m) {
        appSharedPrefHelper.updateStartAt(D, h, m);
    }

    @Override
    public int[] retrieveStartAt() {
        return appSharedPrefHelper.retrieveStartAt();
    }

    @Override
    public void updateEndAt(int h, int m) {
        appSharedPrefHelper.updateEndAt(h, m);
    }

    @Override
    public int[] retrieveEndAt() {
        return appSharedPrefHelper.retrieveEndAt();
    }

    @Override
    public void updateStatus(boolean isRunning) {
        appSharedPrefHelper.updateStatus(isRunning);
    }

    @Override
    public boolean retrieveStatus() {
        return appSharedPrefHelper.retrieveStatus();
    }

    @Override
    public void setStartCalendar(int d, int h, int m) {
        appAlarmHelper.setStartCalendar(d, h, m);
    }

    @Override
    public void setEndCalendar(int h, int m) {
        appAlarmHelper.setEndCalendar(h, m);
    }

    @Override
    public long getDeltaMills() {
        return appAlarmHelper.getDeltaMills();
    }

    @Override
    public long getEndInMills() {
        return appAlarmHelper.getEndInMills();
    }

    @Override
    public long getStartInMills() {
        return appAlarmHelper.getStartInMills();
    }

    @Override
    public void startAlarm(PendingIntent pendingIntent) {
        appAlarmHelper.startAlarm(pendingIntent);
    }

    @Override
    public void stopAlarm(PendingIntent pendingIntent) {
        appAlarmHelper.stopAlarm(pendingIntent);
    }
}
