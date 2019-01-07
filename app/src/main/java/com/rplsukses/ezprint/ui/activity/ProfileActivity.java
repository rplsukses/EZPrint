package com.rplsukses.ezprint.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.network.config.Config;
import com.rplsukses.ezprint.bl.network.model.User;
import com.rplsukses.ezprint.bl.util.PrefUtil;
import com.rplsukses.ezprint.ui.helper.DrawerMenuHelper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.drawerLayout)DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView)NavigationView mNavView;
    @BindView(R.id.activity_profile_toolbar)Toolbar mToolbar;
    @BindView(R.id.activity_profile_tvNama)EditText mTvNama;
    @BindView(R.id.activity_profile_tvEmail)EditText mTvEmail;
    @BindView(R.id.activity_profile_tvTelp)EditText mTvTelp;
    @BindView(R.id.activity_profile_btnSimpan)Button btnSimpan;
    @BindView(R.id.activity_profile_btnEdit)Button btnEdit;
    @BindView(R.id.activity_profile_ivIcon)CircleImageView ivIcon;

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

        mTvNama.setText(user.getNama());
        mTvEmail.setText(user.getEmail());
        mTvTelp.setText(user.getTelepon());
        Picasso.get().load(Config.API_ICON_PROFILE + user.getFoto()).into(ivIcon);
        setEditableFalse();
    }

    private void setEditableFalse(){
        mTvNama.setTag(mTvNama.getKeyListener());
        mTvNama.setKeyListener(null);
        mTvEmail.setTag(mTvEmail.getKeyListener());
        mTvEmail.setKeyListener(null);
        mTvTelp.setTag(mTvTelp.getKeyListener());
        mTvTelp.setKeyListener(null);
    }

    @OnClick(R.id.activity_profile_btnEdit) public void editProfile(){
        mTvTelp.setKeyListener((KeyListener) mTvTelp.getTag());
        mTvNama.setKeyListener((KeyListener) mTvNama.getTag());
        mTvEmail.setKeyListener((KeyListener) mTvEmail.getTag());
        btnSimpan.setVisibility(View.VISIBLE);
        btnEdit.setEnabled(false);
    }

    @OnClick(R.id.activity_profile_btnSimpan) public void updatePorfile(){
        setEditableFalse();
        btnSimpan.setVisibility(View.GONE);
        btnEdit.setEnabled(true);
    }
}
