package labib.com.salatmvp.data.sharePreferences;

import android.content.SharedPreferences;

import labib.com.salatmvp.Logy;

public class AppSharedPrefHelper implements SharedPrefHelper {

    private static final String N_OF_PRAYERS = "nOfPrays";
    private static final String ACTIVE_N_OF_PRAYERS = "activeNofPrays";

    private static final String START_AT_D = "startD";
    private static final String START_AT_H = "startH";
    private static final String START_AT_M = "startM";

    private static final String END_AT_H = "endH";
    private static final String END_AT_M = "endM";
    private static final String STATUS = "status";
    
    private SharedPreferences sharedPreferences;

    public AppSharedPrefHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void updateNofPrayers(int nOfPrayers) {
        sharedPreferences.edit().putInt(N_OF_PRAYERS, nOfPrayers).apply();
        Logy.i(nOfPrayers);
    }

    @Override
    public int retrieveNofPrayers() {
        return sharedPreferences.getInt(N_OF_PRAYERS, 100);
    }

    @Override
    public void updateActiveNoPrayers(int n) {
        sharedPreferences.edit().putInt(ACTIVE_N_OF_PRAYERS, n).apply();
    }

    @Override
    public int retrieveActiveNofPrayer() {
        return sharedPreferences.getInt(ACTIVE_N_OF_PRAYERS, 100);
    }

    @Override
    public void updateStartAt(int d, int h, int m) {

        sharedPreferences.edit().putInt(START_AT_D, d).apply();
        sharedPreferences.edit().putInt(START_AT_H, h).apply();
        sharedPreferences.edit().putInt(START_AT_M, m).apply();
    }

    @Override
    public int[] retrieveStartAt() {
        int d = sharedPreferences.getInt(START_AT_D, 5);
        int h = sharedPreferences.getInt(START_AT_H, 20);
        int m = sharedPreferences.getInt(START_AT_M, 0);
        return new int[]{d, h, m};
    }

    @Override
    public void updateEndAt(int h, int m) {
        sharedPreferences.edit().putInt(END_AT_H, h).apply();
        sharedPreferences.edit().putInt(END_AT_M, m).apply();
    }

    @Override
    public int[] retrieveEndAt() {
        int h = sharedPreferences.getInt(END_AT_H, 17);
        int m = sharedPreferences.getInt(END_AT_M, 0);
        return new int[]{h, m};
    }

    @Override
    public void updateStatus(boolean isRunning) {
        sharedPreferences.edit().putBoolean(STATUS, isRunning).apply();

    }

    @Override
    public boolean retrieveStatus() {
        return sharedPreferences.getBoolean(STATUS, false);
    }


}
