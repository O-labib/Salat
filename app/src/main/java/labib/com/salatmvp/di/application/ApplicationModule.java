package labib.com.salatmvp.di.application;


import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import labib.com.salatmvp.data.AppDataManager;
import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.data.alarmManager.AppAlarmHelper;
import labib.com.salatmvp.data.sharePreferences.AppSharedPrefHelper;

@Module
public class ApplicationModule {


    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Named("applicationContext")
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }


    @Provides
    @Singleton
    SharedPreferences provideSharedPref(@Named("applicationContext") Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    AppSharedPrefHelper provideSharedHelper(SharedPreferences sharedPreferences) {
        return new AppSharedPrefHelper(sharedPreferences);
    }

    @Provides
    @Singleton
    AlarmManager provideAlarmManager(@Named("applicationContext") Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    @Provides
    @Singleton
    AppAlarmHelper provideAlarmHelper(AlarmManager alarmManager,
                                      @Named("startCalendar") Calendar startCalendar,
                                      @Named("endCalendar") Calendar endCalendar) {
        return new AppAlarmHelper(alarmManager, startCalendar, endCalendar);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppSharedPrefHelper appSharedPrefHelper, AppAlarmHelper appAlarmHelper) {
        return new AppDataManager(appSharedPrefHelper, appAlarmHelper);
    }

    @Provides
    @Singleton
    @Named("startCalendar")
    Calendar provideStartCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    @Provides
    @Singleton
    @Named("endCalendar")
    Calendar provideEndCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }
}
