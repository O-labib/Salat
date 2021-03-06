package labib.com.salatmvp.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import labib.com.salatmvp.App;
import labib.com.salatmvp.di.activity.ActivityComponent;
import labib.com.salatmvp.di.activity.ActivityModule;
import labib.com.salatmvp.di.activity.DaggerActivityComponent;
import labib.com.salatmvp.utils.AppUtils;


public abstract class BaseActivity<T extends BaseMvpPresenter> extends AppCompatActivity implements BaseMvpView {

    ActivityComponent activityComponent;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentResource());

        ButterKnife.bind(this);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .activityModule(new ActivityModule(this)).build();

        injectDependencies();
        mPresenter.attach(this);
        init(savedInstanceState);


    }

    public ActivityComponent getComponent() {
        return activityComponent;
    }

    public T getPresenter() {
        return mPresenter;
    }


    @LayoutRes
    protected abstract int getContentResource();

    protected abstract void init(@Nullable Bundle state);

    protected abstract void injectDependencies();

    @Override
    public void showMessage(String s) {
        AppUtils.customToast(s, this);
    }
}
