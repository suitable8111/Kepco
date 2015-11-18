package com.yeho.kimdaeho.lostfindapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KimDaeho on 15. 11. 13..
 */

//전화로 신고하는 화면

public class FifthFragment extends Fragment {
    private int page;

    public static FifthFragment newInstance(int page) {
        FifthFragment fragmentFifth = new FifthFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        fragmentFifth.setArguments(args);
        return fragmentFifth;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fifth_fragment, container, false);
        return view;
    }
}
