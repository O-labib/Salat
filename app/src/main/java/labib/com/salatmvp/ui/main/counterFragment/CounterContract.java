package labib.com.salatmvp.ui.main.counterFragment;

import labib.com.salatmvp.ui.base.BaseMvpDialog;
import labib.com.salatmvp.ui.base.BaseMvpPresenter;

public interface CounterContract {

    interface View extends BaseMvpDialog {
        void updateCounterTV(String s);

        void updateSeekbarProgress(int i);
    }

    interface Presenter extends BaseMvpPresenter<CounterContract.View> {
        void onSeekBarChanged(int i);

        void initCounterTV();

        void saveNofPrayers(int i);
    }


}
