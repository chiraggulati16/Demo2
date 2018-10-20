package com.example.hp.demo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.demo.CoinDetail;
import com.example.hp.demo.Fragments.FragmentA;
import com.example.hp.demo.Model.CoinFavoritesStructures;
import com.example.hp.demo.Model.ListItem;
import com.example.hp.demo.R;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import java.lang.ref.WeakReference;
import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    private String imageUrl="https://s2.coinmarketcap.com/static/img/coins/128x128/";
   List<ListItem> listItems;

    private Context context;
    public static final String Coin_Name="name";
    public static final String Coin_Id="id";
    public static final String Coin_Symbol="symbol";
    public static final String Coin_Price="price";
    public static final String Coin_Price_Btc="btcPrice";
    public static final String Coin_Change_one="changeOne";
    public static final String Coin_Change_hour="changeHour";
    public static final String Coin_Change_seven="changeSeven";
    public static final String Coin_Volume="volume";
    public static final String Coin_Market="market";
    public static final String Coin_Total_Supply="totalSupply";
    public static final String Coin_Max_Supply="maxSupply";
    public static final String Coin_Circulatory_Supply="circulatorySupply";
    public static final String Coin_Website="website";

    public CoinAdapter(List<ListItem> listItems, Context context) {
        this.listItems=listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.ViewHolder holder, final int position) {
        final ListItem list=listItems.get(position);
        Picasso.get()
                .load(new String(imageUrl).concat(Integer.toString(Integer.parseInt(list.getId()))).concat(".png")).into(holder.imageView);
        holder.rankView.setText(list.getRank());
        holder.name.setText(list.getName().concat("(").concat(list.getSymbol()).concat(")"));
        holder.price.setText("$ ".concat(list.getPrice()) );
        holder.volume.setText("$ ".concat(list.getVolume24h()));
        holder.market.setText("$ ".concat(list.getMarketCap()));
        holder.oneHour.setText(list.getPercentChange1h());
        holder.twentyFourHour.setText(list.getPercentChange24h());
        holder.sevenDay.setText(list.getPercentChange7d());
        if(list.getPercentChange1h().contains("-")) {
            holder.oneHour.setTextColor(Color.parseColor("#ff1a1a"));
        }
        else
        {
            holder.oneHour.setTextColor(Color.parseColor("#00FF00"));
        }
        if(list.getPercentChange24h().contains("-")) {
            holder.twentyFourHour.setTextColor(Color.parseColor("#ff1a1a"));
        }
        else
        {
            holder.twentyFourHour.setTextColor(Color.parseColor("#00FF00"));
        }
        if(list.getPercentChange7d().contains("-")) {
            holder.sevenDay.setTextColor(Color.parseColor("#ff1a1a"));
        }
        else
        {
            holder.sevenDay.setTextColor(Color.parseColor("#00FF00"));
        }
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CoinDetail.class);
               intent.putExtra(Coin_Name, list.getName());
               intent.putExtra(Coin_Id, list.getId());
               intent.putExtra(Coin_Symbol, list.getSymbol());
               intent.putExtra(Coin_Price, list.getPrice());
               intent.putExtra(Coin_Price_Btc, list.getBtcPrice());
               intent.putExtra(Coin_Change_hour, list.getPercentChange1h());
               intent.putExtra(Coin_Change_one, list.getPercentChange24h());
               intent.putExtra(Coin_Change_seven, list.getPercentChange7d());
               intent.putExtra(Coin_Circulatory_Supply, list.getCirculatorySupply());
               intent.putExtra(Coin_Max_Supply, list.getMaxSupply());
               intent.putExtra(Coin_Total_Supply, list.getTotalSupply());
               intent.putExtra(Coin_Volume, list.getVolume24h());
               intent.putExtra(Coin_Market, list.getMarketCap());
               intent.putExtra(Coin_Website, list.getWebsite());
               context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
       TextView rankView;
        ImageView imageView;
        TextView name;
        TextView price;
        TextView volume;
        TextView market;
        TextView oneHour;
        TextView twentyFourHour;
        TextView sevenDay;
        public LinearLayout parentLayout;
        private MaterialFavoriteButton favButton;

        public ViewHolder(View itemView) {
            super(itemView);

            rankView=itemView.findViewById(R.id.idText);
            imageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.coin_name);
            price=itemView.findViewById(R.id.price_value);
            volume=itemView.findViewById(R.id.vol_value);
            market=itemView.findViewById(R.id.market_value);
            oneHour=itemView.findViewById(R.id.one_hour_value);
            twentyFourHour=itemView.findViewById(R.id.twenty_four_value);
            sevenDay=itemView.findViewById(R.id.seven_day_value);
            parentLayout=itemView.findViewById(R.id.linearLayout);
            favButton=itemView.findViewById(R.id.currencyListFavButton);
        }


    }
}
