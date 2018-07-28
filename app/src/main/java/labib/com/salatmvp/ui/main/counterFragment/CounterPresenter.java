package labib.com.salatmvp.ui.main.counterFragment;

import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.ui.base.BasePresenter;

public class CounterPresenter extends BasePresenter<CounterContract.View> implements CounterContract.Presenter {

    public CounterPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void initCounterTV() {
        int savedNofPrayers = getDataManager().retrieveNofPrayers();
        getView().updateCounterTV(String.valueOf(savedNofPrayers));
        getView().updateSeekbarProgress(savedNofPrayers/20);
    }

    @Override
    public void onSeekBarChanged(int i) {
        int n = i * 20;
        getView().updateCounterTV(String.valueOf(n));
    }

    @Override
    public void saveNofPrayers(int i) {
        int n = i * 20;
        getDataManager().updateNofPrayers(n);
        getView().dismissDialog();
    }


}
