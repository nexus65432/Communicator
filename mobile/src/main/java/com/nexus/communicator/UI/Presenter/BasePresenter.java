package com.nexus.communicator.UI.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created on 4/11/18.
 */
public interface BasePresenter {

    /**
     * Attach to the View, this will bind a communication between UI
     * @param context
     */
     void setupView(@NonNull Context context);

    /**
     * Reset any values and make room for GC to cleanup any used resources
     */
     void cleanupView();
}
