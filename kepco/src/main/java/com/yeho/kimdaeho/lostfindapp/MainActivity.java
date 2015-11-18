package com.yeho.kimdaeho.lostfindapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //FragementPagerAdapter라는 클래스 선언 미리 객체를 생성함 null
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private  final static int NUM_ITEMS = 5;

        FirstFragment firstFragment = null;
        SecondFragment secondFragment = null;
        ThirdFragment thirdFragment = null;
        FourFragment fourFragment = null;
        FifthFragment fifthFragment = null;

        public MyPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    firstFragment = FirstFragment.newInstance(0);
                    return firstFragment;
                case 1:
                    secondFragment = SecondFragment.newInstance(1);
                    return  secondFragment;
                case 2:
                    thirdFragment = ThirdFragment.newInstance(2);
                    return  thirdFragment;
                case 3:
                    fourFragment = FourFragment.newInstance(3);
                    return  fourFragment;
                case 4:
                    fifthFragment = FifthFragment.newInstance(4);
                    return  fifthFragment;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "신고리스트";
                case 1:
                    return "나의 신고";
                case 2:
                    return "신고 등록";
                case 3:
                    return "환경설정 ";
                case 4:
                    return "전화신고";
                default:
                    return null;
            }
        }
    }
}
