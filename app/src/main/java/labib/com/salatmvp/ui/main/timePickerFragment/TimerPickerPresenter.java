package labib.com.salatmvp.ui.main.timePickerFragment;

import java.util.Calendar;

import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.ui.base.BasePresenter;

public class TimerPickerPresenter extends BasePresenter<TimePickerContract.View> implements TimePickerContract.Presenter {

    public TimerPickerPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSavedStart() {
        int[] saved = getDataManager().retrieveStartAt();
        int hour = saved[1];
        int min = saved[2];

        if (hour == -1 & min == -1) {
            hour = 20;
            min = 0;
        }
        getView().initViews(hour, min);
    }

    @Override
    public void getSavedEnd() {
        int[] saved = getDataManager().retrieveEndAt();
        int hour = saved[0];
        int min = saved[1];

        if (hour == -1 & min == -1) {
            hour = 17;
            min = 0;
        }
        getView().initViews(hour, min);
    }

    @Override
    public void updateStart(int h, int m) {
        int d = (h < 17) ? Calendar.FRIDAY : Calendar.THURSDAY;

        getDataManager().saveStartAt(d, h, m);
    }

    @Override
    public void updateEnd(int h, int m) {
        getDataManager().saveEndAt(h, m);
    }

    @Override
    public void onTimePickerChanged(int h) {
        String s = (h < 17) ? "يوم الجمعة" : "ليلة الخميس ";
        getView().updateDayTV(s);
    }
}
