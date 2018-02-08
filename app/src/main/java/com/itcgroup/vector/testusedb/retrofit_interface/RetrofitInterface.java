package com.itcgroup.vector.testusedb.retrofit_interface;

import com.itcgroup.vector.testusedb.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by VECTOR on 07/02/2018.
 */

public interface RetrofitInterface {

    @POST("/customers")
    Call<Player> savePlayer(@Body Player player);

    @POST("/customers")
    Call<List<Player>> saveListPlayers(@Body List<Player> players);

}
