package com.example.hp.demo.Interfaces;

import com.example.hp.demo.CMCChart.CmcChart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChartInterface {

    @GET
    Call<CmcChart> getChartHistory(@Url String url1);
}
