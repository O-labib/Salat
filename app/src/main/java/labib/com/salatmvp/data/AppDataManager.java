package labib.com.salatmvp.data;

import labib.com.salatmvp.data.sharePreferences.AppSharedPrefHelper;


public class AppDataManager implements DataManager {


    private AppSharedPrefHelper appSharedPrefHelper;

    public AppDataManager(AppSharedPrefHelper appSharedPrefHelper) {
        this.appSharedPrefHelper = appSharedPrefHelper;
    }


    @Override
    public void saveNofPrayers(int nOfPrayers) {
        appSharedPrefHelper.saveNofPrayers(nOfPrayers);
    }

    @Override
    public int retrieveNofPrayers() {
        return appSharedPrefHelper.retrieveNofPrayers();
    }

    @Override
    public void saveStartAt(int D, int h, int m) {
        appSharedPrefHelper.saveStartAt(D,h,m);
    }

    @Override
    public int[] retrieveStartAt() {
        return appSharedPrefHelper.retrieveStartAt();
    }

    @Override
    public void saveEndAt(int h, int m) {
        appSharedPrefHelper.saveEndAt(h,m);
    }

    @Override
    public int[] retrieveEndAt() {
        return appSharedPrefHelper.retrieveEndAt();
    }
}
