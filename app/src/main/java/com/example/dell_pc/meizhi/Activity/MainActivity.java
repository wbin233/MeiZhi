package com.example.dell_pc.meizhi.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.dell_pc.meizhi.Fragment.MeizhiFragment;
import com.example.dell_pc.meizhi.Fragment.ZhuangbiFragment;
import com.example.dell_pc.meizhi.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);
        toolBar.setTitle("福利");

        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ZhuangbiFragment();
                    default:
                        return new MeizhiFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "装逼";
                    default:
                        return "妹纸";
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}
