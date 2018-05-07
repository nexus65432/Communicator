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
public class ChatViewHolder extends RecyclerView.ViewHolder {

    public ImageView mChatUserProfilePic;
    public TextView mChatMessage;
    public TextView mTimeStamp;
    public TextView mMessageStatus;

    public ChatViewHolder(View itemView, @NonNull final AdapterClickListener listener) {
        super(itemView);

        mChatMessage = itemView.findViewById(R.id.chatMessage_tv);
        mTimeStamp = itemView.findViewById(R.id.chatMessageTime_tv);
        mMessageStatus = itemView.findViewById(R.id.chatMessageStatus_tv);

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
