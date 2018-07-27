package labib.com.salatmvp.ui.base;

public interface BaseMvpPresenter<V extends BaseMvpView> {

    void attach(V view);

    void detach();

    boolean isAttached();

}
