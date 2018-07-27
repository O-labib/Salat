package labib.com.salatmvp.data;

import labib.com.salatmvp.data.sharePreferences.AppSharedPrefHelper;


public class AppDataManager implements DataManager {


    private AppSharedPrefHelper appSharedPrefHelper;

    public AppDataManager(AppSharedPrefHelper appSharedPrefHelper) {
        this.appSharedPrefHelper = appSharedPrefHelper;
    }


}
