package labib.com.salatmvp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.salatmvp.Logy;
import labib.com.salatmvp.R;
import labib.com.salatmvp.ui.base.BaseActivity;
import labib.com.salatmvp.ui.main.counterFragment.CounterFragment;
import labib.com.salatmvp.ui.main.timePickerFragment.TimePickerFragment;

import static labib.com.salatmvp.utils.AppConstants.END_SETUP;
import static labib.com.salatmvp.utils.AppConstants.START_SETUP;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.prayerCounterTV)
    TextView counterTV;

    @BindView(R.id.startAtDayTV)
    TextView startAtDayTV;

    @BindView(R.id.startAtHourTV)
    TextView startAtHourTV;

    @BindView(R.id.endAtDayTV)
    TextView endAtDayTV;

    @BindView(R.id.endAtHourTV)
    TextView endAtHourTV;


    @Inject
    FragmentManager fragmentManager;

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

    @OnClick(R.id.setCounterBtn)
    public void setCounterBtn() {
        CounterFragment.newInstance().show(fragmentManager);
    }


    @OnClick(R.id.setStartAtBtn)
    public void setStartAtBtn() {
        TimePickerFragment.newInstance(START_SETUP).show(fragmentManager);
    }


    @OnClick(R.id.setEndAtBtn)
    public void setEndAtBtn() {
        TimePickerFragment.newInstance(END_SETUP).show(fragmentManager);
    }

    @OnClick(R.id.infoBtn)
    public void infoBtn() {

    }

}
