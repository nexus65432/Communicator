package com.nexus.communicator.UI.Implementor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nexus.communicator.Datamodel.Message;
import com.nexus.communicator.UI.Presenter.HomePresenter;
import com.nexus.communicator.UI.Views.HomeView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter {

    private static final String TAG = "HomePresenterImpl";

    private HomeView mHomeView;

    private ResourceObserver<List<Message>> mObservable;
    //private List<Message> messageItemsTemp = new ArrayList<>();

    public HomePresenterImpl(HomeView view) {
        mHomeView = view;
    }

    @Override
    public void setupView(@NonNull Context context) {

    }

    @Override
    public void loadDummyMessageList(@NonNull final String input) {
        Log.d(TAG,"loadDummyMessageList ");
        mObservable = Observable.just(input)
                .subscribeOn(Schedulers.computation())
                .map(new Function<String, List<Message>>() {
                    @Override
                    public List<Message> apply(String newChat) {

                        List<Message> messageItems = new ArrayList<>();

                        for(int i=0; i<5; i++) {
                            Message userMsg = new Message();
                            userMsg.setMessageId(UUID.randomUUID().toString());
                            userMsg.setMessageSource("User " + i);
                            userMsg.setMessagePreview("Message " + i);
                            messageItems.add(userMsg);
                        }

                        //runs in a secondary thread
                        return messageItems;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceObserver<List<Message>>() {
                    @Override
                    public void onNext(List<Message> newChatmsgs) {
                        Log.d(TAG, "Add Dummy chat msgs " + newChatmsgs.size());
                        if (newChatmsgs.size() > 0) {
                            mHomeView.addUserMessages(newChatmsgs);
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

    /*
    @Override
    public void loadDummyChats(@NonNull final String input) {
        Log.d(TAG,"loadDummyChats ");
        mObservable = Observable.just(input)
                .subscribeOn(Schedulers.computation())
                .map(new Function<String, List<ChatMessage>>() {
                    @Override
                    public List<ChatMessage> apply(String newChat) {

                        List<ChatMessage> chatItems = new ArrayList<>();

                        for(int i=0; i<2; i++) {
                            ChatMessage chtmsg = new ChatMessage();
                            chtmsg.setChatId(UUID.randomUUID().toString());
                            chtmsg.setChat_creation_time(DateUtils.getCurrentTime());
                            chtmsg.setChatText("Message " + i);
                            chtmsg.setIncoming(false);

                            chatItems.add(chtmsg);
                        }

                        for(int i=0; i<2; i++) {
                            ChatMessage chtmsg = new ChatMessage();
                            chtmsg.setChatId(UUID.randomUUID().toString());
                            chtmsg.setChat_creation_time(DateUtils.getCurrentTime());
                            chtmsg.setChatText("Message " + i);
                            chtmsg.setIncoming(true);

                            chatItems.add(chtmsg);
                        }

                        for(int i=0; i<2; i++) {
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
                            mHomeView.addNewMessages(newChatmsgs);
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
    */

    @Override
    public void cleanupView() {
        Log.d(TAG, "cleanupView ");
        if (mObservable != null && !mObservable.isDisposed()) {
            mObservable.dispose();
        }
    }
}
