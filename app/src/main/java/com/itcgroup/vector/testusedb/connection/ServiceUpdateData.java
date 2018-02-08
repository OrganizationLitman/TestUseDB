package com.itcgroup.vector.testusedb.connection;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.itcgroup.vector.testusedb.Utils.Util;
import com.itcgroup.vector.testusedb.clientws.RetrofitClient;
import com.itcgroup.vector.testusedb.db.SQLiteDataBaseHandler;
import com.itcgroup.vector.testusedb.model.Player;
import com.itcgroup.vector.testusedb.retrofit_interface.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Servicio que se conectara al iniciar el programa verificara si hay internet
 *
 * Created by VECTOR on 06/02/2018.
 *
 */

public class ServiceUpdateData extends Service {

    private Context context;
    private SQLiteDataBaseHandler db;
    private List<Player> listPlayer;
    RetrofitInterface retrofitInterface;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Iniciar();
    }

    private void Iniciar() {
        Log.d("Entro Servicio", "Inicio Servicio");
        if(Util.isNetworkAvailable(getApplicationContext())){
            db = new SQLiteDataBaseHandler(context);
            listPlayer = db.allPlayers();

            Log.d("Lista", ":"+listPlayer.size());
            if(listPlayer != null && listPlayer.size() > 0){
                //TODO Consumir servicio para actualizar la data de la base de datos
                Log.d("Actualizando", "Se actualizo los datos");

                retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);

                Call<List<Player>> call = retrofitInterface.saveListPlayers(listPlayer);

                call.enqueue(new Callback<List<Player>>() {
                    @Override
                    public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                        List<Player> list = response.body();

                        Log.d("Registro de Lista", "/"+list.size());

                    }

                    @Override
                    public void onFailure(Call<List<Player>> call, Throwable t) {
                        call.cancel();
                    }
                });



            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
