package labib.com.salatmvp.ui.main.counterFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.salatmvp.R;
import labib.com.salatmvp.ui.base.BaseDialog;


public class CounterFragment extends BaseDialog<CounterContract.Presenter> implements CounterContract.View, SeekBar.OnSeekBarChangeListener {


    private static final String TAG = "CounterDialog";


    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @BindView(R.id.counterTV)
    TextView counterTV;


    public static CounterFragment newInstance() {
        CounterFragment fragment = new CounterFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getContentResource() {
        return R.layout.fragment_set_counter;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        seekBar.setOnSeekBarChangeListener(this);
        getPresenter().initCounterTV();
    }

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.okBtn)
    public void submit() {
        getPresenter().saveNofPrayers(seekBar.getProgress());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (i == 0) {
            i = 1;
            seekBar.setProgress(i);
        }
        getPresenter().onSeekBarChanged(i);
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void updateCounterTV(String s) {
        counterTV.setText(s);
    }

    @Override
    public void updateSeekbarProgress(int i) {
        seekBar.setProgress(i);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG);
    }

}
