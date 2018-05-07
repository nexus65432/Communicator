package com.nexus.communicator.UI.Onboarding;

import android.support.annotation.StringRes;

import com.nexus.communicator.UI.Views.OnboardingView;

/**
 * Presenter defines the list of tasks which the Implementer can do
 */
public class OnboardingPresenterImpl implements OnboardingPresenter {

    private OnboardingView mSplashView;

    public OnboardingPresenterImpl(OnboardingView view) {
        this.mSplashView = view;
    }

    @Override
    public void onLoginSuccess() {
        mSplashView.openMainActivity();
    }

    @Override
    public void onLoginFailed() {
        mSplashView.openLoginActivity();
    }

    @Override
    public void onDetach() {

    }
}
