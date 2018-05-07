package com.nexus.communicator.Datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 4/11/18.
 */

public class ChatMessage {

    String chatId;

    String chat_creation_time;

    String chatText;

    String userProfileUrl;

    boolean isIncoming;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChat_creation_time() {
        return chat_creation_time;
    }

    public void setChat_creation_time(String chat_creation_time) {
        this.chat_creation_time = chat_creation_time;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public boolean isIncoming() {
        return isIncoming;
    }

    public void setIncoming(boolean incoming) {
        isIncoming = incoming;
    }
}
