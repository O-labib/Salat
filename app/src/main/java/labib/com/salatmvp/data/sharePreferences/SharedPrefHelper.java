package labib.com.salatmvp.data.sharePreferences;

public interface SharedPrefHelper {

    void saveNofPrayers(int counter);

    int retrieveNofPrayers();

    void saveStartAt(int D,int h,int m);

    int[] retrieveStartAt();

    void saveEndAt(int h,int m);

    int[] retrieveEndAt();

}
