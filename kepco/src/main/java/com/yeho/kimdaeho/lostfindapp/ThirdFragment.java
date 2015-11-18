package com.yeho.kimdaeho.lostfindapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by KimDaeho on 15. 11. 13..
 */

//사용자가 전기 안전 신고를 할 수 있는 화면

public class ThirdFragment extends Fragment implements View.OnClickListener {
    private int page;
    //이미지 버튼
    //카테고리 1 : 까치집발견
    //카테고리 2 : 수목접촉
    //카테고리 3 : 전기설비불량
    //카테고리 4 : 전선높이부족
    //카테고리 5 : 전기선근접작업
    //카테고리 6 : 도로굴착작업
    //카테고리 7 : 전주 기울어짐
    //카테고리 8 : 화재 발생
    //카테고리 9 : 기타 신고

    ImageButton[] categoryButtons = new ImageButton[9];

    public static ThirdFragment newInstance(int page){
        ThirdFragment fragmentThird = new ThirdFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        fragmentThird.setArguments(args);
        return fragmentThird;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);

        for (int i = 0; i < categoryButtons.length*2; i++){
            if(i%2 == 0) {
                categoryButtons[i/2] = (ImageButton) view.findViewById(R.id.category_btn_1+i);
                categoryButtons[i/2].setOnClickListener(this);
            }
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt",0);
    }
    //id값을 비교하여 카테고리 리스트를 넘김
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), ReportActivity.class);
        switch (v.getId()){
            case R.id.category_btn_1:
                intent.putExtra("categoryPage",1);
                break;
            case R.id.category_btn_2:
                intent.putExtra("categoryPage",2);
                break;
            case R.id.category_btn_3:
                intent.putExtra("categoryPage",3);
                break;
            case R.id.category_btn_4:
                intent.putExtra("categoryPage",4);
                break;
            case R.id.category_btn_5:
                intent.putExtra("categoryPage",5);
                break;
            case R.id.category_btn_6:
                intent.putExtra("categoryPage",6);
                break;
            case R.id.category_btn_7:
                intent.putExtra("categoryPage",7);
                break;
            case R.id.category_btn_8:
                intent.putExtra("categoryPage",8);
                break;
            case R.id.category_btn_9:
                intent.putExtra("categoryPage",9);
                break;
        }
        startActivity(intent);
    }
}
