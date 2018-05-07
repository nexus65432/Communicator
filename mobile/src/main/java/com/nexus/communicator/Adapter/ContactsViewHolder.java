package com.nexus.communicator.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexus.communicator.Listener.AdapterClickListener;
import com.nexus.communicator.R;


/**
 * A ViewHolder object stores each of the component views inside the tag field of the Layout,
 * so you can immediately access them without the need to look them up repeatedly
 */
public class ContactsViewHolder extends RecyclerView.ViewHolder {

    public ImageView mUserProfilePic;
    public TextView mUserName;

    public ContactsViewHolder(View itemView, @NonNull final AdapterClickListener listener) {
        super(itemView);

        mUserProfilePic = itemView.findViewById(R.id.contact_profile_pic);
        mUserName = itemView.findViewById(R.id.contact_username);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position, view);
                    }
                }
            }
        });
    }
}
