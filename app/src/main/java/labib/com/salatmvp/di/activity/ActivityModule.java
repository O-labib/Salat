package labib.com.salatmvp.di.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import labib.com.salatmvp.data.DataManager;
import labib.com.salatmvp.ui.main.MainContract;
import labib.com.salatmvp.ui.main.MainPresenter;
import labib.com.salatmvp.ui.main.counterFragment.CounterContract;
import labib.com.salatmvp.ui.main.counterFragment.CounterPresenter;
import labib.com.salatmvp.ui.main.timePickerFragment.TimePickerContract;
import labib.com.salatmvp.ui.main.timePickerFragment.TimerPickerPresenter;

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


    @Provides
    CounterContract.Presenter provideCounterPresenter(DataManager dataManager) {
        return new CounterPresenter(dataManager);
    }

    @Provides
    TimePickerContract.Presenter provideTimepickerPresenter(DataManager dataManager) {
        return new TimerPickerPresenter(dataManager);
    }

    @Provides
    @PerActivity
    FragmentManager provideFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
