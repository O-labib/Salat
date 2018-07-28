package labib.com.salatmvp.data.sharePreferences;

public interface SharedPrefHelper {

    void updateNofPrayers(int counter);

    int retrieveNofPrayers();

    void updateActiveNoPrayers(int n);

    int retrieveActiveNofPrayer();

    void updateStartAt(int D, int h, int m);

    int[] retrieveStartAt();

    void updateEndAt(int h, int m);

    int[] retrieveEndAt();

    void updateStatus(boolean isRunning);

    boolean retrieveStatus();

}
