package com.nexus.communicator.UI.Presenter;

import android.support.annotation.NonNull;

/**
 * Created on 4/11/18.
 */

public interface ChatPresenter extends BasePresenter {

    /**
     * Load sample chat items
     */
    void loadDummyChats(@NonNull final String input);
}
