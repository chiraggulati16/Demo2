package com.example.hp.demo;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hp.demo.Fragments.FragmentA;
import com.example.hp.demo.Fragments.FragmentB;
import com.example.hp.demo.Fragments.FragmentC;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        myAdapter=new MyAdapter(getSupportFragmentManager());
        myAdapter.addFragments(new FragmentA(),"Price");
        myAdapter.addFragments(new FragmentB(),"Events");
        myAdapter.addFragments(new FragmentC(),"Recos");
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
 class MyAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();

    public void addFragments(Fragment fragments, String titles) {
        this.fragments.add(fragments);
        this.titles.add(titles);
    }

    public MyAdapter(FragmentManager fm) {
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
