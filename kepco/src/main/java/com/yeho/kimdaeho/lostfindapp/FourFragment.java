package com.yeho.kimdaeho.lostfindapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KimDaeho on 15. 11. 13..
 */

//환경설정 화면

public class FourFragment extends Fragment{
    private int page;

    public static FourFragment newInstance(int page) {
        FourFragment fragmentFour = new FourFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        fragmentFour.setArguments(args);
        return fragmentFour;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.four_fragment, container, false);
        return view;
    }
}