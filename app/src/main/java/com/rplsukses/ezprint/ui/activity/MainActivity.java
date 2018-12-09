package com.rplsukses.ezprint.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.User;
import com.rplsukses.ezprint.bl.db.model.UserData;
import com.rplsukses.ezprint.bl.util.PrefUtil;
import com.rplsukses.ezprint.ui.adapter.TabAdapter;
import com.rplsukses.ezprint.ui.fragment.KategoriFragment;
import com.rplsukses.ezprint.ui.fragment.LocationFragment;
import com.rplsukses.ezprint.ui.helper.DrawerMenuHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private TabAdapter mAdapter;

    @BindView(R.id.activity_main_viewPager)
    ViewPager mViewPager;
    @BindView(R.id.activity_main_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView) NavigationView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    // This method to initialaze view
    private void init() {
        setSupportActionBar(mToolbar);
        mAdapter = new TabAdapter(getSupportFragmentManager());

        // Set Drawerlayout switch indicator that the icon leftmost Toolbar
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.lbl_open, R.string.lbl_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        // Set navigation item selected listener
        DrawerMenuHelper.navListener(this, mNavView, mDrawerLayout);

        //mTvUserName.setText("Nama");

        mAdapter.addFragment(new LocationFragment(), getString(R.string.tab_location));
        mAdapter.addFragment(new KategoriFragment(), getString(R.string.tab_category));
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    // Override method for search menu toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
