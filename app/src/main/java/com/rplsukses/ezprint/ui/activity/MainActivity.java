package com.rplsukses.ezprint.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.dao.MitraDao;
import com.rplsukses.ezprint.bl.db.dao.ProdukDao;
import com.rplsukses.ezprint.bl.db.helper.Db;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.bl.network.api.Api;
import com.rplsukses.ezprint.bl.network.api.SyncWorker;
import com.rplsukses.ezprint.bl.network.config.RetrofitBuilder;
import com.rplsukses.ezprint.ui.adapter.TabAdapter;
import com.rplsukses.ezprint.ui.dialog.DialogBuilder;
import com.rplsukses.ezprint.ui.fragment.KategoriFragment;
import com.rplsukses.ezprint.ui.fragment.LocationFragment;
import com.rplsukses.ezprint.ui.helper.DrawerMenuHelper;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{
    public static final int STORAGE_PERMISSION = 20;

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

    private Api mApi;
    private boolean isFirstRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Db.getInstance().init(this);
        mApi = RetrofitBuilder.builder(this).create(Api.class);
        initialCheck();
        init();
        new DoCloudSync(this).execute();

        //ask permission to read storage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Recieved.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private class DoCloudSync extends AsyncTask<Void, Void, Void>{
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
            try {
                SyncWorker.getSyncWorker().syncMitra(ctx, mApi.getMitra(), isFirstRun);
                SyncWorker.getSyncWorker().syncProduk(ctx, mApi.getProduk(), isFirstRun);
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

    private void initialCheck(){
        try {
            List<Mitra> mitrasCheck = MitraDao.getMitraDao().read();
            List<Produk> produksCheck = ProdukDao.getProdukDao().read();
            isFirstRun = (mitrasCheck.isEmpty()? true : produksCheck.isEmpty()? true : false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.i("ISFIRSTRUN", String.valueOf(isFirstRun));
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}
