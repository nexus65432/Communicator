package com.nexus.communicator.UI.Views;


import android.support.annotation.StringRes;

public interface BaseView {

    /**
     * Show progress bar
     */
    void showLoading();

    /**
     * Hide progress bar
     */
    void stopLoading();

    /**
     * Update UI in case of error with resource
     * @param resource
     */
    void onError(@StringRes int resource);
}
