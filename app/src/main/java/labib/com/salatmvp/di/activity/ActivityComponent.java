package labib.com.salatmvp.di.activity;

import dagger.Component;
import labib.com.salatmvp.di.application.ApplicationComponent;
import labib.com.salatmvp.ui.main.MainActivity;
import labib.com.salatmvp.ui.main.counterFragment.CounterFragment;
import labib.com.salatmvp.ui.main.timePickerFragment.TimePickerFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity target);

    void inject(CounterFragment target);

    void inject(TimePickerFragment target);


}
