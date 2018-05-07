package com.nexus.communicator.Adapter;

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
public class MessageViewHolder extends RecyclerView.ViewHolder {

    public ImageView mChatUserProfilePic;
    public TextView mUserName;
    public TextView mMessagePreview;

    public MessageViewHolder(final View itemView, final AdapterClickListener listener) {
        super(itemView);

        mChatUserProfilePic = itemView.findViewById(R.id.message_profile_pic);
        mUserName = itemView.findViewById(R.id.message_user_name);
        mMessagePreview = itemView.findViewById(R.id.message_user_message);

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
