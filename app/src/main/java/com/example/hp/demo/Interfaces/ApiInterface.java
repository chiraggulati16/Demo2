package com.example.hp.demo.Interfaces;

import com.example.hp.demo.GlobalPojo.Data;
import com.example.hp.demo.GraphHistory.ChartData;
import com.example.hp.demo.Pojo.Coin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<Coin> getCoins(@Url String url);

    @GET("/v2/global/")
    Call<Data> getTotal();




}

