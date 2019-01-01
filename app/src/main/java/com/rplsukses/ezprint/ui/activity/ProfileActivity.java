package com.rplsukses.ezprint.ui.activity;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.network.model.User;
import com.rplsukses.ezprint.bl.util.PrefUtil;
import com.rplsukses.ezprint.ui.helper.DrawerMenuHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.drawerLayout)DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView)NavigationView mNavView;
    @BindView(R.id.activity_profile_toolbar)Toolbar mToolbar;
    @BindView(R.id.activity_profile_tvNama)TextView mTvNama;
    @BindView(R.id.activity_profile_tvEmail)TextView mTvEmail;
    @BindView(R.id.activity_profile_tvTelp)TextView mTvTelp;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        init();
    }

    // This method to initialaze view
    private void init(){
        setSupportActionBar(mToolbar);

        // Set Drawerlayout switch indicator that the icon leftmost Toolbar
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.lbl_open, R.string.lbl_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        // Set navigation item selected listener
        DrawerMenuHelper.navListener(this, mNavView, mDrawerLayout);

        mTvNama.setText(user.getNama() + " | id : " + user.getId_user());
        mTvEmail.setText(user.getEmail());
        mTvTelp.setText(user.getTelepon());

    }
}
