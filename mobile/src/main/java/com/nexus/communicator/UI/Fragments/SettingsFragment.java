package com.nexus.communicator.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexus.communicator.R;
import com.nexus.communicator.UI.Share.ShareWithFriends;


public class SettingsFragment extends BaseFragment implements View.OnClickListener {

    private CardView mShareFriends;

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_main, container, false);

        mShareFriends = (CardView) rootView.findViewById(R.id.card_view_share);
        mShareFriends.setOnClickListener(this);

        stopLoading();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.card_view_share:
                Intent intent = ShareWithFriends.getStartIntent(getContext());
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
