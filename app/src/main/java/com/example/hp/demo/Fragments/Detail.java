package com.example.hp.demo.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.demo.Adapters.CoinAdapter;
import com.example.hp.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Detail extends Fragment {

    TextView symbol, price_usd, price_btc, volume, market, avail_supply, total_supply, max_supply, change1H, change1D, change7D;

    public Detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        symbol=view.findViewById(R.id.tableNameDataTextView);
        price_usd=view.findViewById(R.id.tablePriceUSDDataTextView);
        price_btc=view.findViewById(R.id.tablePriceBTCDataTextView);
        volume=view.findViewById(R.id.tableVolUSDDataTextView);
        market=view.findViewById(R.id.tableMktCapDataTextView);
        avail_supply=view.findViewById(R.id.tableAvailableSupplyDataTextView);
        total_supply=view.findViewById(R.id.tableTotalSupplyDataTextView);
        max_supply=view.findViewById(R.id.tableMaxSupplyDataTextView);
        change1H=view.findViewById(R.id.table1hrChangeDataTextView);
        change1D=view.findViewById(R.id.table24hrChangeDataTextView);
        change7D=view.findViewById(R.id.tableWeekChangeDataTextView);

        getDetail();

        return view;
    }


      private void getDetail() {
        Intent intent=getActivity().getIntent();

        String sym=intent.getStringExtra(CoinAdapter.Coin_Symbol);
        String priceUsd=intent.getStringExtra(CoinAdapter.Coin_Price);
        String vol=intent.getStringExtra(CoinAdapter.Coin_Volume);
        String mark=intent.getStringExtra(CoinAdapter.Coin_Market);
        String circulatory=intent.getStringExtra(CoinAdapter.Coin_Circulatory_Supply);
        String totalSupply=intent.getStringExtra(CoinAdapter.Coin_Total_Supply);
        String maxSupply=intent.getStringExtra(CoinAdapter.Coin_Max_Supply);
        String change1h=intent.getStringExtra(CoinAdapter.Coin_Change_hour);
        String change1d=intent.getStringExtra(CoinAdapter.Coin_Change_one);
        String change7d=intent.getStringExtra(CoinAdapter.Coin_Change_seven);
        String priceBtc=intent.getStringExtra(CoinAdapter.Coin_Price_Btc);

        symbol.setText(sym);
        price_usd.setText("$ "+priceUsd);
        volume.setText("$ "+vol);
        market.setText("$ "+mark);
        avail_supply.setText(circulatory);
        total_supply.setText(totalSupply);
        if(maxSupply=="") {
            max_supply.setText("N/A");
        }
        else {
            max_supply.setText(maxSupply);
        }
        change1H.setText(change1h.concat("%"));
        change1D.setText(change1d.concat("%"));
        change7D.setText(change7d.concat("%"));
        price_btc.setText("฿ "+priceBtc);

        if(change1h.contains("-")) {
            change1H.setTextColor(Color.parseColor("#ff1a1a"));
        }
        else
        {
            change1H.setTextColor(Color.parseColor("#00FF00"));
        }

        if(change1d.contains("-")) {
            change1D.setTextColor(Color.parseColor("#ff1a1a"));
        }
        else
        {
            change1D.setTextColor(Color.parseColor("#00FF00"));
        }

        if(change7d.contains("-")) {
            change7D.setTextColor(Color.parseColor("#ff1a1a"));
        }
        else
        {
            change7D.setTextColor(Color.parseColor("#00FF00"));
        }

    }
}

