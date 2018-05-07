package com.nexus.communicator.UI.Views;

import android.support.annotation.NonNull;

import com.nexus.communicator.Datamodel.ChatMessage;
import com.nexus.communicator.Datamodel.Message;

import java.util.List;

/**
 * Created on 4/11/18.
 */
public interface ChatView {

    void addChatMessages(@NonNull List<ChatMessage> results);

    void showEmptyList();
}
