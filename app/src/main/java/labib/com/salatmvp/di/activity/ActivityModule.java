package labib.com.salatmvp.di.activity;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.ui.main.MainContract;
import labib.com.salatmvp.ui.main.MainPresenter;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(Context context) {
        mActivity = (AppCompatActivity) context;
    }


    @Provides
    @PerActivity
    MainContract.Presenter provideMainPresenter(DataManager dataManager) {
        return new MainPresenter(dataManager);
    }

}
