package com.nexus.communicator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexus.communicator.Datamodel.ChatMessage;
import com.nexus.communicator.Listener.AdapterClickListener;
import com.nexus.communicator.R;
import com.nexus.communicator.Utils.DateUtils;

import java.util.List;

/**
 * Adapter providing a binding ChatViewHolder data set to views that are displayed in List
 */
public class ChatsAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private static final String TAG = "ChatsAdapter";

    private final static int CHAT_INCOMING = 1;
    private final static int CHAT_OUTGOING = 2;

    private Context mContext;
    private List<ChatMessage> mChatItems;
    private LayoutInflater mInflater;
    private AdapterClickListener mOnItemClickListener;


    public ChatsAdapter(@NonNull Context context, @NonNull List<ChatMessage> chats) {
        mContext = context;
        mChatItems = chats;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(@NonNull final AdapterClickListener clickListener) {
        mOnItemClickListener = clickListener;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case CHAT_INCOMING:
                view = mInflater.inflate(R.layout.chat_message_incoming, parent, false);
                break;
            default:
                view = mInflater.inflate(R.layout.chat_message_outgoing, parent, false);
                break;
        }

        return new ChatViewHolder(view, mOnItemClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        if (mChatItems.get(position).isIncoming()) {
            return CHAT_INCOMING;
        } else {
            return CHAT_OUTGOING;
        }
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {

        final ChatMessage chatObj = mChatItems.get(position);

        if (chatObj != null) {
            holder.mChatMessage.setText(chatObj.getChatText());
            //holder.mChatUserProfilePic
            holder.mTimeStamp.setText(DateUtils.getCurrentTime());
        }

    }

    public void addToList(List<ChatMessage> ChatMessages) {
        mChatItems = ChatMessages;
    }

    public List<ChatMessage> getChatItems() {
        return mChatItems;
    }

    @Override
    public int getItemCount() {
        int retVal = 0;
        if (mChatItems != null) {
            retVal = mChatItems.size();
        }
        return retVal;
    }
}
