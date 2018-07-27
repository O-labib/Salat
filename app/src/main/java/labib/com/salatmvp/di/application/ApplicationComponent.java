package labib.com.salatmvp.di.application;

import javax.inject.Singleton;

import dagger.Component;
import labib.com.salatmvp.App;
import labib.com.salatmvp.data.DataManager;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


    void inject(App app);

    DataManager dataManager();
}
