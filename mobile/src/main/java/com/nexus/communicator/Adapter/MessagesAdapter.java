package com.nexus.communicator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexus.communicator.Datamodel.Message;
import com.nexus.communicator.Listener.AdapterClickListener;
import com.nexus.communicator.R;

import java.util.List;

/**
 * Adapter providing a binding MessageViewHolder data set to views that are displayed in List
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private static final String TAG = "MessagesAdapter";

    private Context mContext;
    private List<Message> mMessageItems;
    private LayoutInflater mInflater;
    private AdapterClickListener mOnItemClickListener;

    public MessagesAdapter(@NonNull Context context, @NonNull List<Message> messages) {
        mContext = context;
        mMessageItems = messages;
        Log.d(TAG,"MessagesAdapter " + mOnItemClickListener);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(@NonNull final AdapterClickListener clickListener) {
        mOnItemClickListener = clickListener;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.message_list_item, parent, false);
        return new MessageViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder " + position);

        final Message msgObj = mMessageItems.get(position);
        if (msgObj != null) {
            holder.mUserName.setText(msgObj.getMessageSource());
            holder.mMessagePreview.setText(msgObj.getMessagePreview());
        }
    }

    public void addList(List<Message> messages) {
        mMessageItems = messages;
    }

    @Override
    public int getItemCount() {
        int retVal = 0;
        if (mMessageItems != null) {
            retVal = mMessageItems.size();
        }
        return retVal;
    }
}
