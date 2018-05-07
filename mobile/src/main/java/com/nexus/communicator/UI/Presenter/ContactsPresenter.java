package com.nexus.communicator.UI.Presenter;


import android.app.Activity;
import android.content.Context;

import com.nexus.communicator.Datamodel.ContactObj;

import java.util.List;

public interface ContactsPresenter extends BasePresenter {

    boolean shouldAskUserPermissions(Activity activity);

    void fetchContacts(Context context);

    List<ContactObj> getContactsList();
}
