package com.nexus.communicator.Listener;

import android.view.View;

/**
 * Click listener to connect Adapter and the Fragment via ViewHolder for a RecyclerList.
 */
public interface AdapterClickListener {

    /**
     * User click on Item on the adapter list
     * @param position
     * @param view
     */
    void onItemClick(int position, View view);
}
