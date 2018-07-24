package labib.com.salatmvp.ui.main;

import labib.com.salatmvp.Logy;
import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.ui.base.BasePresenter;
import labib.com.salatmvp.utils.AppUtils;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void initViews() {

        // counter
        int nOfPrayers = getDataManager().retrieveNofPrayers();
        String nPrayers = nOfPrayers + " مرة";

        // start
        int[] start = getDataManager().retrieveStartAt();
        int startD = start[0];
        int startH = start[1];
        int startM = start[2];

        String startDay = getDay(startD);
        String startHour = getMinute(startM) + " : " + getHour(startH);

        // end

        int[] end = getDataManager().retrieveEndAt();
        int endH = end[0];
        int endM = end[1];

        String endHour = getMinute(endM) + " : " + getHour(endH);
        getView().updateViews(nPrayers, startDay, startHour, endHour);
    }

    @Override
    public void initStatus() {
        boolean isRunning = getDataManager().retrieveStatus();
        String btnText = (isRunning) ? "توقف" : "ابدا";
        getView().updateStatus(btnText, isRunning);
    }

    @Override
    public void startAlarm() {
        int[] start = getDataManager().retrieveStartAt();
        getDataManager().setStartCalendar(start[0], start[1], start[2]);

        int[] end = getDataManager().retrieveEndAt();
        getDataManager().setEndCalendar(end[0], end[1]);

        long deltaMills = getDataManager().getDeltaMills();
        Logy.i(deltaMills);

        if (deltaMills <= 0) {
            getView().showMessage("خطأ فى ضبط التوقيت ،، قم باعادة الضبط ");
            return;
        }

        int nPrayers = getDataManager().retrieveNofPrayers();
        if (deltaMills / nPrayers < 30000) {
            getView().showMessage("الوقت بين التنبيهات قصير جدا ،، قم باعادة الضبط ");
            return;
        }

        getDataManager().updateActiveNoPrayers(getDataManager().retrieveActiveNofPrayer());
        getDataManager().startAlarm();

        getDataManager().updateStatus(true);
        initStatus();
    }

    @Override
    public void stopAlarm() {
        getDataManager().stopAlarm();

        getDataManager().updateStatus(false);
        initStatus();
    }

    @Override
    public void showNextAlerts() {
        String message;
        if (getDataManager().retrieveStatus()) {
            message = AppUtils.calendarsToReadableDate(getDataManager().getStartInMills(), getDataManager().getEndInMills());
        } else {
            message = "قم بتفعيل التنبيه اولا!";
        }
        getView().showMessage(message);
    }


    private String getMinute(int m) {
        String minute;
        if (m < 10) {
            minute = "0" + String.valueOf(m);
        } else {
            minute = String.valueOf(m);
        }
        return minute;
    }

    private String getDay(int d) {
        String day;
        if (d == 6) {
            day = "يوم الجمعة";
        } else {
            day = "ليلة الجمعة";
        }
        return day;
    }

    private String getHour(int h) {
        String hour = null;
        String amOrPm = null;

        if (h == 0) {
            hour = "12";
            amOrPm = "ص";
        } else if (h > 0 & h < 12) {
            hour = String.valueOf(h);
            amOrPm = "ص";
        } else if (h == 12) {
            hour = "12";
            amOrPm = "م";
        } else if (h > 12) {
            h = h - 12;
            hour = String.valueOf(h);
            amOrPm = "م";
        }
        return hour + " " + amOrPm;
    }

    @Override
    public void nextView(int i) {
        i = i + 1;
        getView().walkThrough(i);
    }
}
