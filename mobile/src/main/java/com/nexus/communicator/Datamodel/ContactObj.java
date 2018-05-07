package com.nexus.communicator.Datamodel;

/**
 * Created on 4/11/18.
 */

public class ContactObj {

    String contactId;

    String userName;

    String userProfilePic;

    public ContactObj(String contactId, String userName, String userProfilePic) {
        this.contactId = contactId;
        this.userName = userName;
        this.userProfilePic = userProfilePic;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }
}
