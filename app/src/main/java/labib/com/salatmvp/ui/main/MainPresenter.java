package labib.com.salatmvp.ui.main;

import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
