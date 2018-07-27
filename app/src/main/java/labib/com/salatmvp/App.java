package labib.com.salatmvp;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

import javax.inject.Inject;

import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.di.application.ApplicationComponent;
import labib.com.salatmvp.di.application.ApplicationModule;
import labib.com.salatmvp.di.application.DaggerApplicationComponent;


public class App extends Application {

    @Inject
    DataManager dataManager;

    static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

        updateOrientation();
    }

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
            updateOrientation();
    }

    private void updateOrientation() {
        Configuration configuration = new Configuration();
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.locale = locale;
        } else {
            configuration.setLocale(locale);
        }
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

    }

}
