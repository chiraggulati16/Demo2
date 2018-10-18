package com.example.hp.demo.Interfaces;

import com.example.hp.demo.GraphHistory.ChartData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChartInterface {

    @GET
    Call<ChartData> getChartHistory(@Url String url1);
}
