package labib.com.salatmvp.di.application;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import labib.com.salatmvp.data.AppDataManager;
import labib.com.salatmvp.data.DataManager;
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
    DataManager provideDataManager(AppSharedPrefHelper appSharedPrefHelper) {
        return new AppDataManager(appSharedPrefHelper);
    }
}
