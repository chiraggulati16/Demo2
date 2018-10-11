package com.example.hp.demo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.demo.Adapters.CoinAdapter;
import com.example.hp.demo.Fragments.Alert;
import com.example.hp.demo.Fragments.Chart;
import com.example.hp.demo.Fragments.Detail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CoinDetail extends AppCompatActivity {

    ImageView symbol;
    TextView nameText;
    private String imageUrl="https://s2.coinmarketcap.com/static/img/coins/128x128/";

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    MyCoinAdapter myCoinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);

        toolbar=findViewById(R.id.toolBar1);
        setSupportActionBar(toolbar);

        tabLayout=findViewById(R.id.tabLayout1);
        viewPager=findViewById(R.id.viewPager1);

        myCoinAdapter=new MyCoinAdapter(getSupportFragmentManager());
        myCoinAdapter.addFragments(new Chart(),"Chart");
        myCoinAdapter.addFragments(new Detail(),"Detail");
        myCoinAdapter.addFragments(new Alert(),"Alert");
        viewPager.setAdapter(myCoinAdapter);
        tabLayout.setupWithViewPager(viewPager);

        symbol=findViewById(R.id.img);
        nameText=findViewById(R.id.nameView);



        Intent intent=getIntent();
        String name=intent.getStringExtra(CoinAdapter.Coin_Name);
        int id=Integer.parseInt(intent.getStringExtra(CoinAdapter.Coin_Id));

        nameText.setText(name);
        Picasso.get()
                .load(new String(imageUrl).concat(Integer.toString(id)).concat(".png")).into(symbol);
    }
}
class MyCoinAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();

    public void addFragments(Fragment fragments, String titles) {
        this.fragments.add(fragments);
        this.titles.add(titles);
    }

    public MyCoinAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

