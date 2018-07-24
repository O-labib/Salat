package labib.com.salatmvp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import labib.com.salatmvp.R;

public final class AppUtils {

    private AppUtils() {
    }

    public static void customToast(String s, Activity activity) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View layout = inflater.inflate(R.layout.layout_custom_toast,
                (ViewGroup) activity.findViewById(R.id.toastContainer));

        TextView text = layout.findViewById(R.id.toastTV);

        text.setText(s);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 240);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static String calendarsToReadableDate(long startCalendarInMills, long endCalendarInMills) {
        Date startDate = new Date(startCalendarInMills);
        Date endDate = new Date(endCalendarInMills);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd-MM hh:mm a");

        String nextStartingDate = simpleDateFormat.format(startDate);
        String[] nextStartingDateElements = nextStartingDate.split(" ", 2);
        String startingDay = nextStartingDateElements[0];
        String startingHour = nextStartingDateElements[1];

        String nextEndDate = simpleDateFormat.format(endDate);

        String nextStartTitle = "يبدا التنبيه القادم يوم ال";

        String nextEndTitle = "ويتنهى يوم ال";
        String line = "\n";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(nextStartTitle).append(startingDay).append(line)
                .append(startingHour).append(line).append(nextEndTitle).append(nextEndDate);
        return stringBuilder.toString();
    }


    public static Calendar setToNext(Calendar c, int dayToBeSet) {

        int currentDay = c.get(Calendar.DAY_OF_WEEK);

        if (currentDay == Calendar.FRIDAY && dayToBeSet == Calendar.THURSDAY) {
            c.add(Calendar.DAY_OF_WEEK, -1);
            return c;
        }
        while (currentDay != dayToBeSet) {
            c.add(Calendar.DAY_OF_WEEK, 1);
            currentDay = c.get(Calendar.DAY_OF_WEEK);
        }

        return c;

    }

}
