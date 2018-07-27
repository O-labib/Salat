package labib.com.salatmvp.di.service;

import dagger.Component;
import labib.com.salatmvp.di.application.ApplicationComponent;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {


}
