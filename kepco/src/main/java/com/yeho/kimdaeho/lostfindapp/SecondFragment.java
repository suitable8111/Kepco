package com.yeho.kimdaeho.lostfindapp;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KimDaeho on 15. 11. 12..
 */
//나의 신고리스트를 볼 수 있는 화면

public class SecondFragment extends Fragment {

    private int page;

    public static SecondFragment newInstance(int page){
        SecondFragment fragmentSecond = new SecondFragment();
        Bundle args = new Bundle();
        args.putInt("someInt",page);
        fragmentSecond.setArguments(args);
        return  fragmentSecond;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt",0);

    }
}
