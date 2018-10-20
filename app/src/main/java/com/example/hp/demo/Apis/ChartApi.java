package com.example.hp.demo.Apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartApi {
    public static final String CHART_URL="https://graphs2.coinmarketcap.com/currencies/";
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
