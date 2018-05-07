package com.nexus.communicator.UI.Presenter;

import android.support.annotation.NonNull;


public interface HomePresenter extends BasePresenter {

    /**
     * Load sample chat items
     */
    void loadDummyMessageList(@NonNull final String input);
}
