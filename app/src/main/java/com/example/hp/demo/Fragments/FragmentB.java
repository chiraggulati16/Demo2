package com.example.hp.demo.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.demo.Adapters.EventsAdapter;
import com.example.hp.demo.Interfaces.ApiInterface;
import com.example.hp.demo.Apis.EventsApi;
import com.example.hp.demo.EventsPojo.GetEvents;
import com.example.hp.demo.Interfaces.EventsInterface;
import com.example.hp.demo.Model.EventsItem;
import com.example.hp.demo.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private List<EventsItem> itemList;
    private EventsInterface eventsInterface;
    Boolean isScrolling=true;
    int totalItems, currentItems, scrollOutItems;
    int offset=1;


    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_b, container, false);

        recyclerView=view.findViewById(R.id.recycle);
        linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        itemList=new ArrayList<>();
        eventsInterface= EventsApi.eventsApiClient().create(EventsInterface.class);
        eventsAdapter=new EventsAdapter(getContext(), itemList);
        recyclerView.setAdapter(eventsAdapter);

        getEvents("1");
        return view;
    }

    private void getEvents(final String startItem) {

        final String access_token="MjA4NDA4NzFjNzgyYjg1NjljOWNhMTcyZWIxYjc4MzcxYjU3Y2IwODVhOTY1ZTMxMWU1MzU0MTU4NDdkNWMzOA";

        Call<List<GetEvents>> call=eventsInterface
                .getEvents("/v1/events?access_token="+access_token+"&page="+startItem+"&max=100");
        call.enqueue(new Callback<List<GetEvents>>() {
            @Override
            public void onResponse(Call<List<GetEvents>> call, Response<List<GetEvents>> response) {
                List<GetEvents> events=response.body();
                for(int i=0; i<events.size(); i++) {
                    String title=events.get(i).getTitle();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    String date=events.get(i).getDateEvent();
                    String createDate=events.get(i).getCreatedDate();

                    Date date1= null;
                    Date date2=null;
                    try {
                        date1 = sdf.parse(date);
                        date2=sdf.parse(createDate);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf2=new SimpleDateFormat("dd MMMM yyyy");
                    String finalDate=sdf2.format(date1);
                    String finalCreateDate=sdf2.format(date2);

                    String description=events.get(i).getDescription();
                    String proof=events.get(i).getProof();
                    String source=events.get(i).getSource();
                    int vote=events.get(i).getVoteCount();
                    int percentage=events.get(i).getPercentage();
                    for(int j=0; j<1 ; j++) {
                        String id=events.get(i).getCoins().get(j).getId();
                        String name=events.get(i).getCoins().get(j).getName();
                        String symbol=events.get(i).getCoins().get(j).getSymbol();
                        EventsItem ev=new EventsItem(id, name, symbol,finalDate, finalCreateDate, description, proof, source, vote, percentage, title);
                        itemList.add(ev);
                        eventsAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<GetEvents>> call, Throwable t) {

            }
        });
    }

}
