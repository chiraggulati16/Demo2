package com.example.hp.demo.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.TextView;

import com.example.hp.demo.Apis.ApiClient;
import com.example.hp.demo.GlobalPojo.Data;
import com.example.hp.demo.Interfaces.ApiInterface;
import com.example.hp.demo.Adapters.CoinAdapter;
import com.example.hp.demo.DividerItemDecorator;
import com.example.hp.demo.Model.ListItem;
import com.example.hp.demo.Pojo.Coin;
import com.example.hp.demo.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private CoinAdapter adapter;
    private List<ListItem> items;
    private List<ListItem> items2;
    private ApiInterface apiInterface;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    int index;
    int offset = 1;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView textView;

    public FragmentA() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        items = new ArrayList<>();
        items2 = new ArrayList<>();
        textView = view.findViewById(R.id.error);
        textView.setVisibility(View.INVISIBLE);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        getData(1);
        adapter = new CoinAdapter(items, getContext());

        recyclerView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(getContext(),
                R.drawable.item_decorator)));

        recyclerView.setAdapter(adapter);




        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener()

        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;

                    // change offset to the number you want to add items....loading 10 items at a time


                    offset = offset + 100;
                    getData(offset);
                }
            }
        });
        return view;
    }

    public void getData(final int startItem) {
        Call<Coin> call=apiInterface.getCoins("/v2/ticker/?start="+startItem+"&limit=100&sort=rank&structure=array");
        call.enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                textView.setVisibility(View.INVISIBLE);
                Coin coin = response.body();
                int total = coin.getMetadata().getNumCryptocurrencies();

                    for (int i = 0; i < coin.getData().size(); i++) {
                        String id = String.valueOf(coin.getData().get(i).getId());
                        String rank = String.valueOf(coin.getData().get(i).getRank());
                        String name = coin.getData().get(i).getName();
                        String symbol = coin.getData().get(i).getSymbol();
                        DecimalFormat decimal = new DecimalFormat("0.00");
                        Double pricevalue = coin.getData().get(i).getQuotes().getUSD().getPrice();
                        String price = decimal.format(pricevalue);
                        Double marketvalue = coin.getData().get(i).getQuotes().getUSD().getMarketCap();
                        String market = decimal.format(marketvalue);
                        Double volumeValue = coin.getData().get(i).getQuotes().getUSD().getVolume24h();
                        String volume = decimal.format(volumeValue);
                        Double oneHourValue = coin.getData().get(i).getQuotes().getUSD().getPercentChange1h();
                        String oneHour = decimal.format(oneHourValue).concat("%");
                        Double twentyHourValue = coin.getData().get(i).getQuotes().getUSD().getPercentChange24h();
                        String twentyHour = decimal.format(twentyHourValue).concat("%");
                        Double sevenDayValue = coin.getData().get(i).getQuotes().getUSD().getPercentChange7d();
                        String sevenDay = decimal.format(sevenDayValue).concat("%");
                        Double cirSupply=coin.getData().get(i).getCirculatingSupply();
                        Double tSupply=coin.getData().get(i).getTotalSupply();
                        String mSupply=coin.getData().get(i).getMaxSupply();
                        String circulatorySupply=decimal.format(cirSupply);
                        String totalSupply=decimal.format(tSupply);


                        if (oneHourValue < 0) {

                        } else {
                            oneHour = "+" + oneHour;

                        }
                        if (twentyHourValue < 0) {
                        } else {
                            twentyHour = "+" + twentyHour;

                        }
                        if (sevenDayValue < 0) {
                        } else {
                            sevenDay = "+" + sevenDay;

                        }
                        ListItem listItem1 = new ListItem(id, rank, name, symbol, price, market, volume,
                                oneHour, twentyHour, sevenDay, totalSupply
                        , mSupply, circulatorySupply);
                        items.add(listItem1);
                        adapter.notifyDataSetChanged();

                    }
                }
            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("No Internet Connection");
                textView.setTextColor(Color.parseColor("#FFFFFF"));

            }
        });

    }


    @Override
    public void onRefresh() {
        Handler handler=new Handler();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                while (offset<=300){

                    getData(offset);
                    offset=offset+100;
                }
            }
        };
        handler.postDelayed(r,2000);

    }
}




