package com.rplsukses.ezprint.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.rplsukses.ezprint.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {
    @BindView(R.id.drawerLayout)DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView)NavigationView mNavView;
    @BindView(R.id.activity_info_toolbar)Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        setSupportActionBar(mToolbar);

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

                //Toast.makeText(getApplicationContext(), msgString, Toast.LENGTH_LONG).show();

                return true;
            }
        });
    }
}
