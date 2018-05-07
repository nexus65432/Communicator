package com.nexus.communicator.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import co.poynt.os.model.Intents;

/**
 * Created on 4/20/18.
 */

public class PoyntCloudMessageReceiver extends BroadcastReceiver {
    public PoyntCloudMessageReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("PoyntCloudMR", "Got cloud Message: " + intent.getStringExtra(Intents
                .INTENT_EXTRA_CLOUD_MESSAGE_BODY));
    }
}