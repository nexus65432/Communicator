package com.nexus.communicator.UI.Implementor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.nexus.communicator.Datamodel.ContactObj;
import com.nexus.communicator.UI.Presenter.ContactsPresenter;
import com.nexus.communicator.UI.Views.ShareView;
import com.nexus.communicator.Utils.ContactFetcher;

import java.util.ArrayList;
import java.util.List;


public class SharePresenterImpl implements ContactsPresenter {

    private ShareView mShareView;
    private List<ContactObj> mContactObjList = new ArrayList<>();

    public SharePresenterImpl(ShareView shareview) {
        mShareView = shareview;
    }

    @Override
    public void setupView(@NonNull Context context) {

    }

    @Override
    public boolean shouldAskUserPermissions(Activity activity) {
        if (mContactObjList != null && mContactObjList.size() > 0) {
            return false;
        } else {
            return ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS);
        }
    }

    @Override
    public void fetchContacts(Context context) {
        mShareView.fetchingContact(true);
        if (getContactsList().size() == 0) {
            mContactObjList = new ContactFetcher(context).fetchAllContacts();
        }
        mShareView.fetchingContact(false);
        mShareView.updateContactsList(mContactObjList);
    }

    @Override
    public List<ContactObj> getContactsList() {
        return mContactObjList;
    }

    @Override
    public void cleanupView() {

    }
}
