package com.nexus.communicator.UI.Implementor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nexus.communicator.Datamodel.ChatMessage;
import com.nexus.communicator.UI.Presenter.ChatPresenter;
import com.nexus.communicator.UI.Views.ChatView;
import com.nexus.communicator.Utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 4/11/18.
 */

public class ChatPresenterImpl implements ChatPresenter {

    private static final String TAG = "ChatPresenterImpl";

    private ChatView mChatView;

    private ResourceObserver<List<ChatMessage>> mObservable;

    public ChatPresenterImpl(ChatView view) {
        mChatView = view;
    }

    @Override
    public void setupView(@NonNull Context context) {

    }

    @Override
    public void loadDummyChats(@NonNull final String input) {
        Log.d(TAG,"loadDummyChats ");
        mObservable = Observable.just(input)
                .subscribeOn(Schedulers.computation())
                .map(new Function<String, List<ChatMessage>>() {
                    @Override
                    public List<ChatMessage> apply(String newChat) {

                        List<ChatMessage> chatItems = new ArrayList<>();

                        for(int i=0; i<4; i++) {
                            ChatMessage chtmsg = new ChatMessage();
                            chtmsg.setChatId(UUID.randomUUID().toString());
                            chtmsg.setChat_creation_time(DateUtils.getCurrentTime());
                            chtmsg.setChatText("Message " + i);
                            chtmsg.setIncoming(false);

                            chatItems.add(chtmsg);
                        }

                        for(int i=0; i<4; i++) {
                            ChatMessage chtmsg = new ChatMessage();
                            chtmsg.setChatId(UUID.randomUUID().toString());
                            chtmsg.setChat_creation_time(DateUtils.getCurrentTime());
                            chtmsg.setChatText("Message " + i);
                            chtmsg.setIncoming(true);

                            chatItems.add(chtmsg);
                        }

                        for(int i=0; i<4; i++) {
                            ChatMessage chtmsg = new ChatMessage();
                            chtmsg.setChatId(UUID.randomUUID().toString());
                            chtmsg.setChat_creation_time(DateUtils.getCurrentTime());
                            chtmsg.setChatText("Message " + i);
                            chtmsg.setIncoming(false);

                            chatItems.add(chtmsg);
                        }

                        //runs in a secondary thread
                        return chatItems;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceObserver<List<ChatMessage>>() {
                    @Override
                    public void onNext(List<ChatMessage> newChatmsgs) {
                        Log.d(TAG, "Add Dummy chat msgs " + newChatmsgs.size());
                        if (newChatmsgs.size() > 0) {
                            mChatView.addChatMessages(newChatmsgs);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "loadDummyChats onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "loadDummyChats onComplete ");
                    }
                });
    }

    @Override
    public void cleanupView() {
        Log.d(TAG, "cleanupView ");
        if (mObservable != null && !mObservable.isDisposed()) {
            mObservable.dispose();
        }
    }
}
