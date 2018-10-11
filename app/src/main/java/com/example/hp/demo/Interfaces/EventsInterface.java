package com.example.hp.demo.Interfaces;

import com.example.hp.demo.EventsPojo.GetEvents;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface EventsInterface {

    @GET
    Call<List<GetEvents>> getEvents(@Url String mUrl);
}
