package com.infosys.connected.h_carev2;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.infosys.connected.h_carev2.Adapter.PageAdapter;

public class cardiac_health_analysis extends AppCompatActivity   {
TabLayout tabLayout;
ViewPager viewPager;
TabItem t1,t2,t3;
PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardiac_health_analysis);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customtoolbar);
         tabLayout = findViewById(R.id.tablayout);
        t1 = findViewById(R.id.tab1);
        t2  = findViewById(R.id.tab2);
        t3 = findViewById(R.id.tab3);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setBackgroundColor(ContextCompat.getColor(cardiac_health_analysis.this,
                R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(cardiac_health_analysis.this,
                    R.color.colorPrimary));
        }
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(cardiac_health_analysis.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(cardiac_health_analysis.this,
                                R.color.colorPrimary));
                    }
                } else if (tab.getPosition() == 2) {

                    tabLayout.setBackgroundColor(ContextCompat.getColor(cardiac_health_analysis.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(cardiac_health_analysis.this,
                                R.color.colorPrimary));
                    }
                } else {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(cardiac_health_analysis.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(cardiac_health_analysis.this,
                                R.color.colorPrimary));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
