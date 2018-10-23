package com.example.hp.demo.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.demo.Adapters.CoinAdapter2;
import com.example.hp.demo.Apis.ApiClient;
import com.example.hp.demo.DividerItemDecorator;
import com.example.hp.demo.Interfaces.ApiInterface;
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
public class FragmentC extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private CoinAdapter2 adapter;
    private List<ListItem> items;
    private List<ListItem> items2;
    private ApiInterface apiInterface;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    int index;
    int offset = 1;
    SwipeRefreshLayout swipeRefreshLayout;
//    TextView textView;

    public FragmentC() {
        // Required empty public constructor
    }

    @Override
    public void onRefresh() {
        getData(1);
    }


    public interface AllCoinsListUpdater {
        void allCoinsModifyFavorites(ListItem coin);
        void performAllCoinsSort();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh1);
        recyclerView = view.findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        items = new ArrayList<>();
        items2 = new ArrayList<>();
//        textView = view.findViewById(R.id.error);
//        textView.setVisibility(View.INVISIBLE);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        adapter = new CoinAdapter2(items, getContext());

        recyclerView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(getContext(),
                R.drawable.item_decorator)));

        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
//
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                getData(1);

            }
        });
        return view;
    }


    public void getData(final int startItem) {
//        swipeRefreshLayout.setRefreshing(true);
        Call<Coin> call = apiInterface.getCoins("/v2/ticker/?start=" + startItem + "&limit=100&sort=rank&structure=array&convert=BTC");
        call.enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
//                textView.setVisibility(View.INVISIBLE);
                Coin coin = response.body();
                int total = coin.getMetadata().getNumCryptocurrencies();
                int count = startItem + 100;
                if (count < 200) {
                    getData(count);
                }
                for (int i = 0; i < coin.getData().size(); i++) {
                    String id = String.valueOf(coin.getData().get(i).getId());
                    String rank = String.valueOf(coin.getData().get(i).getRank());
                    String name = coin.getData().get(i).getName();
                    String symbol = coin.getData().get(i).getSymbol();
                    String website = coin.getData().get(i).getWebsiteSlug();
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
                    Double cirSupply = coin.getData().get(i).getCirculatingSupply();
                    Double tSupply = coin.getData().get(i).getTotalSupply();
                    String mSupply = coin.getData().get(i).getMaxSupply();
                    String circulatorySupply = decimal.format(cirSupply);
                    String totalSupply = decimal.format(tSupply);
                    Double btcValue = coin.getData().get(i).getQuotes().getBTC().getPrice();
                    String btcPrice = decimal.format(btcValue);


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
                            , mSupply, circulatorySupply, btcPrice, website);
                    if (!items.contains(listItem1) && checkPref(listItem1.getId())) {
                        items.add(listItem1);
                    }

                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);

                }
            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
//                textView.setVisibility(View.VISIBLE);
//                textView.setText(t.getMessage());
//                textView.setTextColor(Color.parseColor("#FFFFFF"));

                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private boolean checkPref(String id) {
        SharedPreferences app = getContext().getSharedPreferences("App", 0);
        return app.getBoolean(id, false);

    }
}
