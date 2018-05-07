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

import com.nexus.communicator.Adapter.ChatsAdapter;
import com.nexus.communicator.Datamodel.ChatMessage;
import com.nexus.communicator.Listener.AdapterClickListener;
import com.nexus.communicator.R;
import com.nexus.communicator.UI.Implementor.ChatPresenterImpl;
import com.nexus.communicator.UI.Views.ChatView;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends BaseFragment implements ChatView, AdapterClickListener {

    private static final String TAG = "ChatFragment";

    private ChatsAdapter mChatAdapter;
    private RecyclerView mChatMsgListView;
    private List<ChatMessage> mChatMessages = new ArrayList<>();

    private ChatPresenterImpl mChatPresenterImpl;

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chatview_main, container, false);

        mProgressBar = rootView.findViewById(R.id.base_progress_bar);

        showLoading();
        setUpRecyclerView(rootView);
        mChatPresenterImpl = new ChatPresenterImpl(this);
        stopLoading();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mChatAdapter.getItemCount() == 0) {
            Log.d(TAG,"loadDummyChats ");
            mChatPresenterImpl.loadDummyChats("test");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Setup recyclerView
     */
    private void setUpRecyclerView(View view) {
        mChatAdapter = new ChatsAdapter(getContext(), mChatMessages);
        mChatAdapter.setOnItemClickListener(this);

        mChatMsgListView = view.findViewById(R.id.listView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mChatMsgListView.setItemAnimator(new DefaultItemAnimator());
        mChatMsgListView.setLayoutManager(mLayoutManager);
        mChatMsgListView.setAdapter(mChatAdapter);

    }

    @Override
    public void addChatMessages(@NonNull List<ChatMessage> results) {
        Log.d(TAG,"addNewMessages ");
        if (results != null && results.size() > 0) {
            stopLoading();
            mChatMessages.addAll(results);
            mChatAdapter.addToList(mChatMessages);
            mChatAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showEmptyList() {
        stopLoading();
    }

    @Override
    public void onStop() {
        super.onStop();
        mChatPresenterImpl.cleanupView();
    }

    @Override
    public void onItemClick(int position, View v) {
        Log.d(TAG,"onItemClick " + position);
    }

}
