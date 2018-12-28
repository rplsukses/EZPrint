package com.rplsukses.ezprint.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.bl.network.model.Kategori;
import com.rplsukses.ezprint.ui.adapter.KategoriAdapter;
import com.rplsukses.ezprint.ui.adapter.MitraAdapter;
import com.rplsukses.ezprint.ui.adapter.ProdukAdapter;
import com.rplsukses.ezprint.ui.presenter.MitraPresenter;
import com.rplsukses.ezprint.ui.presenter.ProdukPresenter;
import com.rplsukses.ezprint.ui.view.MitraView;
import com.rplsukses.ezprint.ui.view.ProdukView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MitraActivity extends AppCompatActivity implements MitraView, ProdukView {
    private KategoriAdapter kategoriAdapter;
    private ProdukAdapter produkAdapter;
    private MitraPresenter mitraPresenter;
    private ProdukPresenter produkPresenter;
    private List<Kategori> mList = new ArrayList<>();
    private List<Produk> produkList = new ArrayList<>();
    private Mitra mitra;
    private Integer id_mitra;

    @BindView(R.id.activity_mitra_tvMitra)
    TextView mTvMitra;
    @BindView(R.id.activity_mitra_tvAlamat)
    TextView mTvAlamat;
    @BindView(R.id.activity_mitra_tvJam)
    TextView mTvJam;
    @BindView(R.id.activity_mitra_rvContent)
    RecyclerView mRvContent;
    @BindView(R.id.activity_mitra_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra);
        ButterKnife.bind(this);
        initPresenter();
        init();
    }

    private void init(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        id_mitra = Integer.valueOf(getIntent().getStringExtra("id_mitra"));
        mitra = mitraPresenter.getByID(id_mitra);
        produkAdapter = new ProdukAdapter(this);
        mRvContent.setLayoutManager(new GridLayoutManager(this, 2));

        mTvMitra.setText(mitra.getNama());
        mTvAlamat.setText(mitra.getAlamat());
        mTvJam.setText(mitra.getJam_buka() + " - "  + mitra.getJam_tutup());
    }

    private void initPresenter(){
        mitraPresenter = new MitraPresenter(this);
        produkPresenter = new ProdukPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        produkPresenter.loadProdukByMitra(id_mitra);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadProduk(List<Produk> produkList) {
        produkAdapter.generate(produkList);
        mRvContent.setAdapter(produkAdapter);
    }

    @Override
    public void loadMitra(List<Mitra> mitraList) {

    }
}
