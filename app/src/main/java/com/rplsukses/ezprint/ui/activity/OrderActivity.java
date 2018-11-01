package com.rplsukses.ezprint.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.ui.adapter.TabAdapter;
import com.rplsukses.ezprint.ui.fragment.KategoriFragment;
import com.rplsukses.ezprint.ui.fragment.LocationFragment;

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

    private void init(){
        setSupportActionBar(mToolbar);
        mAdapter = new TabAdapter(getSupportFragmentManager());

        // Set Drawerlayout switch indicator that the icon leftmost Toolbar
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.lbl_open, R.string.lbl_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        // Set navigation item selected listener
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String msgString = "";

                switch (menuItem.getItemId()) {
                    case R.id.navHome:
                        msgString = (String) menuItem.getTitle();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        break;
                    case R.id.navProfile:
                        msgString = (String) menuItem.getTitle();
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        finish();
                        break;
                    case R.id.navOrder:
                        msgString = (String) menuItem.getTitle();
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        finish();
                        break;
                    case R.id.navHelp:
                        msgString = (String) menuItem.getTitle();
                        startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                        finish();
                        break;
                    case R.id.navAboutus:
                        msgString = (String) menuItem.getTitle();
                        startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                        finish();
                        break;
                }

                // Menu item clicked on, and close Drawerlayout
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                Toast.makeText(getApplicationContext(), msgString, Toast.LENGTH_LONG).show();

                return true;
            }
        });

        //mAdapter.addFragment(new LocationFragment(), getString(R.string.tab_location));
        //mAdapter.addFragment(new KategoriFragment(), getString(R.string.tab_category));
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
