package com.itcgroup.vector.testusedb.receivered;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.itcgroup.vector.testusedb.connection.ServiceReceiver;

/**
 * Created by GhostL on 11/02/2018.
 */

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("enter Receiver","Enter oK");
        ComponentName comp = new ComponentName(context.getPackageName(), ServiceReceiver.class.getName());
        intent.putExtra("isNetworkingConnected", isConnected(context));

        context.startService((intent.setComponent(comp)));
    }
    public  boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

}
