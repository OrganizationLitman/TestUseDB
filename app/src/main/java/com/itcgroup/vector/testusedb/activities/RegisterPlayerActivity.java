package com.itcgroup.vector.testusedb.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itcgroup.vector.testusedb.R;
import com.itcgroup.vector.testusedb.Utils.Util;
import com.itcgroup.vector.testusedb.clientws.RetrofitClient;
import com.itcgroup.vector.testusedb.db.SQLiteDataBaseHandler;
import com.itcgroup.vector.testusedb.model.Player;
import com.itcgroup.vector.testusedb.retrofit_interface.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPlayerActivity extends AppCompatActivity {

    private SQLiteDataBaseHandler db;
    Button btnSave;
    Player player;
    EditText name,
            position,
            height;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_player);

        btnSave = (Button) findViewById(R.id.btnSavePlayer);

        name = (EditText) findViewById(R.id.etxtName);
        position = (EditText) findViewById(R.id.etxtPosition);
        height = (EditText) findViewById(R.id.etxtHeight);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Internet", "/"+Util.isNetworkAvailable(getApplicationContext()));
                player = new Player();

                player.setName(name.getText().toString());
                player.setPosition(position.getText().toString());
                player.setHeight(Integer.parseInt(height.getText().toString()));
                
                if(!Util.isNetworkAvailable(getApplicationContext())){
                    Log.d("Retrofit", "Guardar en Servicio");
                    retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                    Call<Player> call = retrofitInterface.savePlayer(player);

                    call.enqueue(new Callback<Player>() {
                        @Override
                        public void onResponse(Call<Player> call, Response<Player> response) {
                            Player player1 = response.body();

                            Log.d("Player Guardado", player1.toString());

                            finish();
                        }

                        @Override
                        public void onFailure(Call<Player> call, Throwable t) {
                            call.cancel();
                        }
                    });
                    

                }else{

                    db = new SQLiteDataBaseHandler(getApplicationContext());

                    

                    Log.d("Player", player.toString());

                    //TODO graba en la BD SQLite
                    db.addPlayer(player);

                    finish();

                }
            }
        });

    }
}
