package com.nexus.communicator.UI.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexus.communicator.Adapter.MessagesAdapter;
import com.nexus.communicator.Datamodel.Message;
import com.nexus.communicator.Listener.AdapterClickListener;
import com.nexus.communicator.Listener.ChatRoomListener;
import com.nexus.communicator.R;
import com.nexus.communicator.UI.Implementor.HomePresenterImpl;
import com.nexus.communicator.UI.Views.HomeView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements HomeView, ChatRoomListener, AdapterClickListener {

    private static final String TAG = "HomeFragment";

    private MessagesAdapter mMessagesAdapter;
    private RecyclerView mMessagesList;
    private List<Message> mMessageItems = new ArrayList<>();

    private HomePresenterImpl mHomePresenterImpl;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_main, container, false);

        mProgressBar = rootView.findViewById(R.id.base_progress_bar);

        showLoading();
        setUpRecyclerView(rootView);
        mHomePresenterImpl = new HomePresenterImpl(this);
        stopLoading();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "loadDummyMessageList ");

        if (mMessagesAdapter.getItemCount() == 0) {
            mHomePresenterImpl.loadDummyMessageList("test");
        }
    }

    /**
     * Setup recyclerView
     */
    private void setUpRecyclerView(View view) {
        mMessagesAdapter = new MessagesAdapter(getContext(), mMessageItems);
        mMessagesAdapter.setOnItemClickListener(this);

        mMessagesList = view.findViewById(R.id.listView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mMessagesList.setItemAnimator(new DefaultItemAnimator());
        mMessagesList.setLayoutManager(mLayoutManager);
        mMessagesList.setAdapter(mMessagesAdapter);
    }

    @Override
    public void addUserMessages(@NonNull List<Message> results) {
        Log.d(TAG, "addNewMessages ");
        if (results != null && results.size() > 0) {
            stopLoading();
            mMessageItems.addAll(results);
            mMessagesAdapter.addList(mMessageItems);
            mMessagesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showEmptyList() {
        stopLoading();
    }

    @Override
    public void onStop() {
        super.onStop();
        mHomePresenterImpl.cleanupView();
    }

    @Override
    public void onItemClick(int position, View v) {
        Log.d(TAG, "onItemClick " + position);
        openChatConversation();
    }

    @Override
    public void openChatConversation() {
        ChatFragment fragment = ChatFragment.newInstance();
        getFragmentManager().beginTransaction()
                .replace(R.id.container_main, fragment)
                .addToBackStack(null)
                .commit();
    }

}
