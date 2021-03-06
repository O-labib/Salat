package labib.com.salatmvp.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import labib.com.salatmvp.di.activity.ActivityComponent;
import labib.com.salatmvp.ui.main.MainActivity;

public abstract class BaseDialog<T extends BaseMvpPresenter> extends android.support.v4.app.DialogFragment implements BaseMvpDialog {

    private BaseActivity mActivity;
    Unbinder unbinder;

    @Inject
    T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;


            injectDependencies();
            if (mPresenter != null) {
                mPresenter.attach(this);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentResource(), container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);

        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        init(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getComponent();
        }
        return null;
    }

    public BaseActivity getHostActivity() {
        if (mActivity != null) {
            return mActivity;
        }
        return null;
    }

    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        this.show(transaction, tag);

    }

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    public void dismissDialog(String tag) {
        dismiss();
        ((MainActivity)getHostActivity()).onFragmentDetached();
    }


    public interface Callbacks {
        void onFragmentDetached();
    }


    @Override
    public void showMessage(String s) {
        if (mActivity != null) {
            mActivity.showMessage(s);
        }
    }

    protected abstract int getContentResource();

    protected abstract void init(@Nullable Bundle state);

    protected abstract void injectDependencies();

}
