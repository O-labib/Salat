package labib.com.salatmvp.ui.main;

import labib.com.salatmvp.ui.base.BaseMvpPresenter;
import labib.com.salatmvp.ui.base.BaseMvpView;

public interface MainContract {

    interface View extends BaseMvpView {
        void updateViews(String counter, String startD, String startH, String endH);

        void updateStatus(String btnText, boolean isRunning);

        void walkThrough(int i);
    }

    interface Presenter extends BaseMvpPresenter<View> {
        void initViews();

        void initStatus();

        void startAlarm();

        void stopAlarm();

        void showNextAlerts();

        void nextView(int i);
    }

}
