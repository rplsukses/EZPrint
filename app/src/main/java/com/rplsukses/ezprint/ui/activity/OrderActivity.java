package com.rplsukses.ezprint.ui.activity;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.ui.adapter.TabAdapter;
import com.rplsukses.ezprint.ui.fragment.HistoryFragment;
import com.rplsukses.ezprint.ui.fragment.OrderFragment;
import com.rplsukses.ezprint.ui.helper.DrawerMenuHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {
    private TabAdapter mAdapter;

    @BindView(R.id.drawerLayout)DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView)NavigationView mNavView;
    @BindView(R.id.activity_order_tabLayout)TabLayout mTabLayout;
    @BindView(R.id.activity_order_toolbar)Toolbar mToolbar;
    @BindView(R.id.activity_order_viewPager)ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        init();
    }

    // This method to initialaze view
    private void init(){
        setSupportActionBar(mToolbar);
        mAdapter = new TabAdapter(getSupportFragmentManager());

        // Set Drawerlayout switch indicator that the icon leftmost Toolbar
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.lbl_open, R.string.lbl_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);


        // Set navigation item selected listener
        DrawerMenuHelper.navListener(this, mNavView, mDrawerLayout);

        mAdapter.addFragment(new OrderFragment(), getString(R.string.tab_inprogress));
        mAdapter.addFragment(new HistoryFragment(), getString(R.string.tab_history));
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
