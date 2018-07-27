package labib.com.salatmvp.di.activity;

import dagger.Component;
import labib.com.salatmvp.ui.main.MainActivity;
import labib.com.salatmvp.di.application.ApplicationComponent;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity target);


}
