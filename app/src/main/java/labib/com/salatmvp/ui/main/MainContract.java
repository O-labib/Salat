package labib.com.salatmvp.ui.main;

import labib.com.salatmvp.ui.base.BaseMvpPresenter;
import labib.com.salatmvp.ui.base.BaseMvpView;

public class MainContract {

    public interface View extends BaseMvpView {

    }

    public   interface Presenter extends BaseMvpPresenter<View> {

    }

}
