package com.nexus.communicator.UI.Fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.nexus.communicator.UI.Views.BaseView;

/**
 * To handle generic functionalists common to all Fragments
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    public ProgressBar mProgressBar;

    @Override
    public void showLoading() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void stopLoading() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(int resId) {

    }
}
