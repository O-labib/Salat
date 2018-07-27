package labib.com.salatmvp.ui.base;

import labib.com.salatmvp.data.DataManager;

public class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    V view;
    private DataManager dataManager;

    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void attach(V view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public boolean isAttached() {
        return this.view != null;
    }

    public V getView() {
        return this.view;
    }
}
