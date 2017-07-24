package com.bigapps.brooklyn.films.view.baseView;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import com.bigapps.brooklyn.films.view.MainContainerCallbacks;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public abstract class BaseFragment extends Fragment implements BaseView{

    protected MainContainerCallbacks containerActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        containerActivity = (MainContainerCallbacks) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        containerActivity = (MainContainerCallbacks) activity;
    }

    @Override
    public void showProgress() {
        containerActivity.showProgress();
    }

    @Override
    public void hideProgress() {
        containerActivity.hideProgress();
    }

    @Override
    public void showError(String error) {
        containerActivity.showError(error);
    }
}
