package labib.com.salatmvp.ui.main;

import android.app.PendingIntent;

import labib.com.salatmvp.ui.base.BaseMvpPresenter;
import labib.com.salatmvp.ui.base.BaseMvpView;

public interface MainContract {

    interface View extends BaseMvpView {
        void updateViews(String counter, String startD, String startH, String endH);

        void updateStatus(String btnText, boolean isRunning);

        PendingIntent alarmPendingIntent();
    }

    interface Presenter extends BaseMvpPresenter<View> {
        void initViews();

        void initStatus();

        void startAlarm();

        void stopAlarm();
    }

}
