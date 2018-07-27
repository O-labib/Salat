package labib.com.salatmvp.data.sharePreferences;

import android.content.SharedPreferences;

public class AppSharedPrefHelper implements SharedPrefHelper {
    private SharedPreferences sharedPreferences;

    public AppSharedPrefHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
