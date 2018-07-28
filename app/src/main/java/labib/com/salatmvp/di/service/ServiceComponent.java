package labib.com.salatmvp.di.service;

import dagger.Component;
import labib.com.salatmvp.di.application.ApplicationComponent;
import labib.com.salatmvp.service.PrayerService;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(PrayerService target);

}
