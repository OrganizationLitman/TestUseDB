package com.itcgroup.vector.testusedb.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itcgroup.vector.testusedb.R;
import com.itcgroup.vector.testusedb.db.SQLiteDataBaseHandler;
import com.itcgroup.vector.testusedb.model.Player;

import java.util.List;

public class ShowPlayersActivity extends AppCompatActivity {

    private SQLiteDataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players);

        db = new SQLiteDataBaseHandler(getApplicationContext());

        // list all players
        List<Player> players = db.allPlayers();

        if (players != null) {
            String[] itemsNames = new String[players.size()];

            for (int i = 0; i < players.size(); i++) {
                itemsNames[i] = players.get(i).toString();
            }

            // display like string instances
            ListView list = (ListView) findViewById(R.id.list);
            list.setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, itemsNames));

        }
    }
}
