package com.nexus.communicator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nexus.communicator.Datamodel.ContactObj;
import com.nexus.communicator.Listener.AdapterClickListener;
import com.nexus.communicator.R;

import java.util.List;

/**
 * Adapter providing a binding ChatViewHolder data set to views that are displayed in List
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {

    private static final String TAG = "ContactsAdapter";

    private Context mContext;
    private List<ContactObj> mContactItems;
    private LayoutInflater mInflater;
    private AdapterClickListener mOnItemClickListener;


    public ContactsAdapter(@NonNull Context context, @NonNull List<ContactObj> contacts) {
        mContext = context;
        mContactItems = contacts;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(@NonNull final AdapterClickListener clickListener) {
        mOnItemClickListener = clickListener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.contact_layout_item, parent, false);
        return new ContactsViewHolder(view, mOnItemClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {

        if (mContactItems != null) {
            final ContactObj contactObj = mContactItems.get(position);

            if (contactObj != null) {
                holder.mUserName.setText(contactObj.getUserName());

                String userThumbnailUri = contactObj.getUserProfilePic();
                if (userThumbnailUri != null) {
                    Glide.with(mContext)
                            .load(userThumbnailUri)
                            .centerCrop()
                            .placeholder(R.drawable.ic_account_circle_black_24dp)
                            .error(R.drawable.ic_account_circle_black_24dp)
                            .into(holder.mUserProfilePic);
                }
            }
        }
    }

    public void addToList(List<ContactObj> contacts) {
        mContactItems = contacts;
    }

    public List<ContactObj> getContacts() {
        return mContactItems;
    }

    @Override
    public int getItemCount() {
        int retVal = 0;
        if (mContactItems != null) {
            retVal = mContactItems.size();
        }
        return retVal;
    }
}
