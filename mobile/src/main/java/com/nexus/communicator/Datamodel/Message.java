package com.nexus.communicator.Datamodel;

public class Message {

    String messageId;

    String messageCreationTime;

    String messageSource;

    String messagePreview;

    String userProfilePic;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageCreationTime() {
        return messageCreationTime;
    }

    public void setMessageCreationTime(String messageCreationTime) {
        this.messageCreationTime = messageCreationTime;
    }

    public String getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(String messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessagePreview() {
        return messagePreview;
    }

    public void setMessagePreview(String messagePreview) {
        this.messagePreview = messagePreview;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }
}
