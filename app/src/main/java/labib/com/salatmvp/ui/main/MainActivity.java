package labib.com.salatmvp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import labib.com.salatmvp.R;
import labib.com.salatmvp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {

    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }
}
