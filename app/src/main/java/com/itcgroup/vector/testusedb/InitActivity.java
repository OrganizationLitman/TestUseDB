package com.itcgroup.vector.testusedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.itcgroup.vector.testusedb.activities.RegisterPlayerActivity;
import com.itcgroup.vector.testusedb.activities.ShowPlayersActivity;
import com.itcgroup.vector.testusedb.db.SQLiteDataBaseHandler;
import com.itcgroup.vector.testusedb.model.Player;

import java.util.List;

public class InitActivity extends AppCompatActivity {


    Button btnSave, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);


        btnSave = (Button) findViewById(R.id.btnGuardar);
        btnShow = (Button) findViewById(R.id.btnMostrar);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSave = new Intent(InitActivity.this, RegisterPlayerActivity.class);

                startActivity(intentSave);

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentShow = new Intent(InitActivity.this, ShowPlayersActivity.class);

                startActivity(intentShow);

            }
        });

    }
}
