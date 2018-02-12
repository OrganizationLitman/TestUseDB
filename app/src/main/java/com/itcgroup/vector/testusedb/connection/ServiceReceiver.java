package com.itcgroup.vector.testusedb.connection;


import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by GhostL on 11/02/2018.
 */

public class ServiceReceiver extends IntentService{


    public ServiceReceiver(){
        super("My receiver");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ServiceReceiver(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Enter Service", "Enter Ok");
        Bundle extras = intent.getExtras();
        boolean isNetworkConnected = extras.getBoolean("isNetworkingConnected");
    }
}
