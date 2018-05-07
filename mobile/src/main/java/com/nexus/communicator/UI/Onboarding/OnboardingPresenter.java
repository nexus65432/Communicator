package com.nexus.communicator.UI.Onboarding;


import com.nexus.communicator.UI.Views.BaseView;

public interface OnboardingPresenter {

    /**
     * On user successfully authenticated
     */
    void onLoginSuccess();

    /**
     * When user authentication failed
     */
    void onLoginFailed();

    /**
     * Cleanup resources when activity is destroyed
     */
    void onDetach();
}
