package labib.com.salatmvp;

import android.util.Log;


public class Logy {

    public static void i(int i) {
        Log.i("Tag", "" + i);
    }

    public static void i(String s) {
        Log.i("Tag", s);
    }

    public static void i(long l) {
        Log.i("Tag", String.valueOf(l));
    }

    public static void i(double d) {
        Log.i("Tag", String.valueOf(d));
    }

    public static void i(boolean b) {
        Log.i("Tag", String.valueOf(b));
    }


}
