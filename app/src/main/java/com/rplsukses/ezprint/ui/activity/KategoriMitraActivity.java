package com.rplsukses.ezprint.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.ui.adapter.MitraAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KategoriMitraActivity extends AppCompatActivity {

    private MitraAdapter mAdapter;
    private List<Mitra> mList = new ArrayList<>();

    @BindView(R.id.activity_kategori_mitra_rvContent)
    RecyclerView mRvContent;
    @BindView(R.id.activity_kategori_mitra_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_kategori_mitra_tvTitle)
    TextView mTvKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_mitra);
        ButterKnife.bind(this);
        init();
        initData();
    }

    public void init(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAdapter = new MitraAdapter(this, 1);
        mAdapter.generate(mList);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.setAdapter(mAdapter);

        mTvKategori.setText(getIntent().getStringExtra("kategori"));
    }

    public void initData(){
        String[] mitra = getResources().getStringArray(R.array.list_mitra);
        for (String s : mitra){
            //mList.add(new MitraGet(s));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
