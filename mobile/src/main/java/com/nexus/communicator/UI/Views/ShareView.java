package com.nexus.communicator.UI.Views;

import com.nexus.communicator.Datamodel.ContactObj;

import java.util.List;


public interface ShareView {

    void fetchingContact(boolean inProgress);

    void updateContactsList(List<ContactObj> contactObjs);
}
