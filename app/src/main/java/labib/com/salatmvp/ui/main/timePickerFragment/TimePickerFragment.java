package labib.com.salatmvp.ui.main.timePickerFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.salatmvp.Logy;
import labib.com.salatmvp.R;
import labib.com.salatmvp.ui.base.BaseDialog;

import static labib.com.salatmvp.utils.AppConstants.END_SETUP;
import static labib.com.salatmvp.utils.AppConstants.START_SETUP;

public class TimePickerFragment extends BaseDialog<TimePickerContract.Presenter> implements TimePickerContract.View, TimePicker.OnTimeChangedListener {

    private static final String TAG = "TimePickerDialog";

    private static final String SOURCE = "Source";


    @BindView(R.id.timePicker)
    TimePicker timePicker;

    @BindView(R.id.dayTV)
    TextView dayTV;

    public static TimePickerFragment newInstance(int source) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(SOURCE, source);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_timepicker;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        switch (whichFragment()) {
            case START_SETUP:
                getPresenter().getSavedStart();
                break;
            case END_SETUP:
                getPresenter().getSavedEnd();
                break;
            default:
                break;
        }
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);


    }


    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }


    @OnClick(R.id.submitBtn)
    public void submit() {

        switch (whichFragment()) {
            case START_SETUP:
                getPresenter().updateStart(retrieveTimePicker()[0], retrieveTimePicker()[1]);
                break;
            case END_SETUP:
                getPresenter().updateEnd(retrieveTimePicker()[0], retrieveTimePicker()[1]);
                break;
            default:
                break;
        }
        dismissDialog();
    }

    private int whichFragment() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getInt(SOURCE);
        }
        return 0;
    }

    @Override
    public void initViews(int h, int m) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(h);
            timePicker.setMinute(m);
        } else {
            timePicker.setCurrentHour(h);
            timePicker.setCurrentMinute(m);
        }
        onTimeChanged(null, h, m);

    }

    int[] retrieveTimePicker() {
        int hour, minute;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }
        return new int[]{hour, minute};
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int hour, int i1) {
        if (whichFragment() == START_SETUP) {
            getPresenter().onTimePickerChanged(hour);
        } else {
            dayTV.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateDayTV(String d) {
        dayTV.setText(d);
    }
}
