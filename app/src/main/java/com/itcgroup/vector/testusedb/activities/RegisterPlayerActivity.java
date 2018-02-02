package com.itcgroup.vector.testusedb.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itcgroup.vector.testusedb.R;
import com.itcgroup.vector.testusedb.Utils.Util;
import com.itcgroup.vector.testusedb.db.SQLiteDataBaseHandler;
import com.itcgroup.vector.testusedb.model.Player;

public class RegisterPlayerActivity extends AppCompatActivity {

    private SQLiteDataBaseHandler db;
    Button btnSave;
    Player player;
    EditText name,
            position,
            height;

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
                if(!Util.isNetworkAvailable(getApplicationContext())){
                    //TODO Deberia ir el codigo de grabar directamente al servicio
                }else{

                    db = new SQLiteDataBaseHandler(getApplicationContext());

                    player = new Player();

                    player.setName(name.getText().toString());
                    player.setPosition(position.getText().toString());
                    player.setHeight(Integer.parseInt(height.getText().toString()));

                    Log.d("Player", player.toString());

                    //TODO graba en la BD SQLite
                    db.addPlayer(player);

                    finish();

                }
            }
        });

    }
}
