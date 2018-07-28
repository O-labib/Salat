package labib.com.salatmvp.ui.main;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.salatmvp.Logy;
import labib.com.salatmvp.R;
import labib.com.salatmvp.service.AlarmReciever;
import labib.com.salatmvp.ui.base.BaseActivity;
import labib.com.salatmvp.ui.base.BaseDialog;
import labib.com.salatmvp.ui.main.counterFragment.CounterFragment;
import labib.com.salatmvp.ui.main.timePickerFragment.TimePickerFragment;

import static labib.com.salatmvp.utils.AppConstants.END_SETUP;
import static labib.com.salatmvp.utils.AppConstants.START_SETUP;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, BaseDialog.Callbacks {

    @BindView(R.id.prayerCounterTV)
    TextView counterTV;

    @BindView(R.id.startAtDayTV)
    TextView startAtDayTV;

    @BindView(R.id.startAtHourTV)
    TextView startAtHourTV;

    @BindView(R.id.endAtHourTV)
    TextView endAtHourTV;

    @BindView(R.id.finalSetBtn)
    Button setButton;

    @BindView(R.id.setEndAtBtn)
    ImageView setEndAtBtn;

    @BindView(R.id.setStartAtBtn)
    ImageView setStartAtBtn;

    @BindView(R.id.setCounterBtn)
    ImageView setCounterBtn;


    @Inject
    FragmentManager fragmentManager;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        getPresenter().initViews();
        getPresenter().initStatus();
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
    public void infoBtn(View view) {


    }


    @OnClick(R.id.finalSetBtn)
    public void submit(View view) {
        boolean isRunning = (boolean) view.getTag();
        if (isRunning) {
            getPresenter().stopAlarm();
        } else {
            getPresenter().startAlarm();
        }
    }

    @Override
    public void updateViews(String counter, String startD, String startH, String endH) {
        counterTV.setText(counter);
        startAtDayTV.setText(startD);
        startAtHourTV.setText(startH);
        endAtHourTV.setText(endH);
    }

    @Override
    public void updateStatus(String btnText, boolean isRunning) {
        animateButtons(isRunning, setStartAtBtn, setEndAtBtn, setCounterBtn);
        setButton.setTag(isRunning);
        setButton.setText(btnText);
    }


    @Override
    public void onFragmentDetached() {
        getPresenter().initViews();
    }

    public static void animateButtons(boolean hide, ImageView... buttons) {
        for (ImageView button : buttons) {
            if (hide) {
                button.animate().alphaBy(-1f).setDuration(300).start();
            } else {
                button.animate().alphaBy(1f).setDuration(300).start();
            }
            button.setClickable(!hide);
            button.animate().rotationBy(360f).setDuration(300).start();
        }
    }

    @Override
    public PendingIntent alarmPendingIntent() {
        Intent alarmIntent = new Intent(this, AlarmReciever.class);
        return PendingIntent.getBroadcast
                (this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
