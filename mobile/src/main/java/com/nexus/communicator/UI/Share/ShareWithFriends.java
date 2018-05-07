package com.nexus.communicator.UI.Share;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nexus.communicator.Adapter.ContactsAdapter;
import com.nexus.communicator.Datamodel.ContactObj;
import com.nexus.communicator.Listener.AdapterClickListener;
import com.nexus.communicator.R;
import com.nexus.communicator.UI.BaseActivity;
import com.nexus.communicator.UI.Implementor.SharePresenterImpl;
import com.nexus.communicator.UI.Views.ShareView;

import java.util.ArrayList;
import java.util.List;

public class ShareWithFriends extends BaseActivity implements ShareView, AdapterClickListener {

    private static final String TAG = "ShareWithFriends";

    public static final int RequestPermissionCode = 1001;

    private Toolbar mToolbar;
    private TextView mToolbarTitle;

    private Button mPermissionsButton;
    private RecyclerView mContactRecyclerListView;
    private ProgressBar mProgressBar;

    private ContactsAdapter mContactsAdapter;

    private SharePresenterImpl mSharePresenterImpl;
    private List<ContactObj> mContactObjList = new ArrayList<>();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ShareWithFriends.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    private void setupToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.shareview_actionbar_title));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.share_friends_layout);

        mSharePresenterImpl = new SharePresenterImpl(this);

        setupToolbar();
        setUpRecyclerView();

        mPermissionsButton = findViewById(R.id.user_permissions_button);

        mContactRecyclerListView = findViewById(R.id.contacts_listView);
        mProgressBar = findViewById(R.id.base_progress_bar);

        if (mSharePresenterImpl.shouldAskUserPermissions(this)) {
            mPermissionsButton.setOnClickListener(mPermissionsListener);
            mProgressBar.setVisibility(View.GONE);
        } else {
            mPermissionsButton.setVisibility(View.GONE);
            mSharePresenterImpl.fetchContacts(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSharePresenterImpl.setupView(this);
    }

    /**
     * Setup recyclerView
     */
    private void setUpRecyclerView() {
        Log.d(TAG, "setUpRecyclerView ");
        mContactsAdapter = new ContactsAdapter(this, mContactObjList);
        mContactsAdapter.setOnItemClickListener(this);

        mContactRecyclerListView = findViewById(R.id.contacts_listView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mContactRecyclerListView.setItemAnimator(new DefaultItemAnimator());
        mContactRecyclerListView.setLayoutManager(mLayoutManager);
        mContactRecyclerListView.setAdapter(mContactsAdapter);
    }

    @Override
    public void onItemClick(int position, View view) {
        Log.d(TAG, "onItemClick " + position);

        if (mContactsAdapter.getContacts() != null) {
            ContactObj userContact = mContactsAdapter.getContacts().get(position);
            Log.d(TAG, "UserName " + userContact.getUserName());
        }
    }

    @Override
    public void fetchingContact(boolean inProgress) {
        if (inProgress) {
            mPermissionsButton.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateContactsList(List<ContactObj> contactObjs) {
        Log.d(TAG, "updateContactsList ");
        if (contactObjs.size() > 0) {
            mContactObjList.addAll(contactObjs);
            mPermissionsButton.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
            mContactRecyclerListView.setVisibility(View.VISIBLE);
            mContactsAdapter.addToList(mContactObjList);
            mContactsAdapter.notifyDataSetChanged();
        } else {
            mContactRecyclerListView.setVisibility(View.GONE);
            mPermissionsButton.setVisibility(View.VISIBLE);
        }
    }

    private View.OnClickListener mPermissionsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityCompat.requestPermissions(ShareWithFriends.this, new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();
                    mSharePresenterImpl.fetchContacts(this);
                } else {
                    Toast.makeText(this, "Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSharePresenterImpl.cleanupView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
