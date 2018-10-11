package com.example.hp.demo.Apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsApi {

    public static final String URL="https://api.coinmarketcal.com";
    public static Retrofit retrofit=null;

    public static Retrofit eventsApiClient() {
        if (retrofit==null) {
            retrofit=new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
