package labib.com.salatmvp.ui.main.timePickerFragment;

import labib.com.salatmvp.ui.base.BaseMvpDialog;
import labib.com.salatmvp.ui.base.BaseMvpPresenter;

public interface TimePickerContract {

    interface View extends BaseMvpDialog {
        void initViews(int h, int m);

        void updateDayTV(String d);
    }

    interface Presenter extends BaseMvpPresenter<View> {

        void getSavedStart();

        void getSavedEnd();

        void updateStart(int h, int m);

        void updateEnd(int h, int m);

        void onTimePickerChanged(int h);

    }

}
