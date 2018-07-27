package labib.com.salatmvp;

import android.app.Application;

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




    }

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }


}
