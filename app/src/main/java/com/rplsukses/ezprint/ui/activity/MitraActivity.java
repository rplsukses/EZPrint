package com.rplsukses.ezprint.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.network.model.Kategori;
import com.rplsukses.ezprint.ui.adapter.KategoriAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MitraActivity extends AppCompatActivity {
    private KategoriAdapter mAdapter;
    private List<Kategori> mList = new ArrayList<>();

    @BindView(R.id.activity_mitra_tvMitra)
    TextView mTvMitra;
    @BindView(R.id.activity_mitra_rvContent)
    RecyclerView mRvContent;
    @BindView(R.id.activity_mitra_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra);
        ButterKnife.bind(this);
        init();
        initData();
    }

    public void init(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAdapter = new KategoriAdapter(this, 1);
        mAdapter.generate(mList);
        mRvContent.setLayoutManager(new GridLayoutManager(this, 2));
        mRvContent.setAdapter(mAdapter);

        mTvMitra.setText(getIntent().getStringExtra("mitra"));
    }

    public void initData(){
        String[] kategori = getResources().getStringArray(R.array.list_kategori);
        for (String s : kategori){
            //mList.add(new Kategori(s));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
