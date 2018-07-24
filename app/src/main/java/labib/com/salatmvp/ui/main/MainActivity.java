package labib.com.salatmvp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.salatmvp.R;
import labib.com.salatmvp.ui.base.BaseActivity;
import labib.com.salatmvp.ui.base.BaseDialog;
import labib.com.salatmvp.ui.main.counterFragment.CounterFragment;
import labib.com.salatmvp.ui.main.timePickerFragment.TimePickerFragment;
import me.toptas.fancyshowcase.DismissListener;
import me.toptas.fancyshowcase.FancyShowCaseView;

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

    @BindView(R.id.infoBtn)
    ImageView infoBtn;


    @Inject
    FragmentManager fragmentManager;

    boolean justStarted = true;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        getPresenter().initViews();
        getPresenter().initStatus();

        walkThrough(0);
    }

    @Override
    public void walkThrough(final int i) {
        String[] walkThroughMessages = getResources().getStringArray(R.array.walkThroughTitles);
        String[] walkThroughCounter = getResources().getStringArray(R.array.walkThroughCounter);
        View[] view = {setCounterBtn, setStartAtBtn, setEndAtBtn, setButton, infoBtn};

        if (i < walkThroughCounter.length) {
            new FancyShowCaseView.Builder(MainActivity.this)
                    .focusOn(view[i])
                    .showOnce(walkThroughCounter[i])
                    .title(walkThroughMessages[i])
                    .backgroundColor(getResources().getColor(R.color.colorShowCase))
                    .titleStyle(R.style.walkThrough, Gravity.CENTER)
                    .dismissListener(new DismissListener() {
                        @Override
                        public void onDismiss(String id) {
                            getPresenter().nextView(i);
                        }

                        @Override
                        public void onSkipped(String id) {

                        }
                    }).build().show();
        }

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
        getPresenter().showNextAlerts();
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

    public void animateButtons(boolean hide, ImageView... buttons) {
        for (ImageView button : buttons) {
            if (hide) {
                if (justStarted) {
                    button.setVisibility(View.GONE);
                    button.animate().alpha(0f).setDuration(300).start();
                } else {
                    button.animate().alpha(0f).setDuration(300).start();
                }
            } else {
                button.setVisibility(View.VISIBLE);
                button.animate().alpha(1f).setDuration(300).start();
            }
            button.setClickable(!hide);
            button.animate().rotationBy(360f).setDuration(300).start();

        }
        justStarted = false;

    }


}
