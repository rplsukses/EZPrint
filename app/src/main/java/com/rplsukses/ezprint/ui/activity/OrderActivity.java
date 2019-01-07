package com.rplsukses.ezprint.ui.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.network.api.Api;
import com.rplsukses.ezprint.bl.network.api.SyncWorker;
import com.rplsukses.ezprint.bl.network.config.RetrofitBuilder;
import com.rplsukses.ezprint.bl.network.model.User;
import com.rplsukses.ezprint.bl.util.PrefUtil;
import com.rplsukses.ezprint.ui.adapter.TabAdapter;
import com.rplsukses.ezprint.ui.dialog.DialogBuilder;
import com.rplsukses.ezprint.ui.fragment.HistoryFragment;
import com.rplsukses.ezprint.ui.fragment.OrderFragment;
import com.rplsukses.ezprint.ui.helper.DrawerMenuHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {
    private TabAdapter mAdapter;
    private Api mApi;
    private User user;
    private boolean isFirstRun = false;

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
        mApi = RetrofitBuilder.builder(this).create(Api.class);
        user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
        init();
        new DoCloudSync(this).execute();
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

    private class DoCloudSync extends AsyncTask<Void, Void, Void> {
        private MaterialDialog dialog;
        private final Context ctx;

        private DoCloudSync(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = DialogBuilder.showLoadingDialog(ctx, "Updating Data", "Please Wait", false);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {;
                SyncWorker.getSyncWorker().syncTransaksi(ctx, mApi.getTransaksi(user.getId_user()), isFirstRun);
                if(isFirstRun) Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }


}
