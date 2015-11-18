package com.yeho.kimdaeho.lostfindapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KimDaeho on 15. 11. 12..
 */

//신고리스트를 보여주는 화면

public class FirstFragment extends Fragment {

    private int page;

    public static FirstFragment newInstance(int page){
        //FirstFragmnet 객체 생성
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt",page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //초기화할때 Bundle 받아온 page를 가져옴
        page = getArguments().getInt("someInt",0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //first_fragment의 xml 페이지를 가져옴
        View view = inflater.inflate(R.layout.first_fragment,container,false);
        return view;
    }
}
