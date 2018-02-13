package com.itcgroup.vector.testusedb.receivered;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.itcgroup.vector.testusedb.connection.ServiceReceiver;
import com.itcgroup.vector.testusedb.connection.ServiceUpdateData;

/**
 *
 * Es un Reciver que escucha el estado de la internet, cuando se conecta o desconecta el internet
 * Created by GhostL on 11/02/2018.
 */

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("enter Receiver","Enter oK");
        if(isConnected(context)) {
            //TODO se inicia el servicio encargado de migrar la data al Servidor
            ComponentName comp = new ComponentName(context.getPackageName(), ServiceUpdateData.class.getName());
            Log.d("isConnected", "//" + isConnected(context));

            context.startService((intent.setComponent(comp)));
        }
    }
    public  boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

}
