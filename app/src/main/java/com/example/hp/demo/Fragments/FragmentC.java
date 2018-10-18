package com.example.hp.demo.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.demo.Model.ListItem;
import com.example.hp.demo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentC extends Fragment {


    public FragmentC() {
        // Required empty public constructor
    }

    public interface AllCoinsListUpdater {
        void allCoinsModifyFavorites(ListItem coin);
        void performAllCoinsSort();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

}
