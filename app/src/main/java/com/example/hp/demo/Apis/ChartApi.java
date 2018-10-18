package com.example.hp.demo.Apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartApi {
    public static final String CHART_URL="https://min-api.cryptocompare.com";
    public static Retrofit retrofit=null;

    public static Retrofit getChartClient()
    {
        if (retrofit==null) {
            retrofit=new Retrofit.Builder().baseUrl(CHART_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
