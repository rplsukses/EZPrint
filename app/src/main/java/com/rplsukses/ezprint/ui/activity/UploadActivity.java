package com.rplsukses.ezprint.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.ui.presenter.MitraPresenter;
import com.rplsukses.ezprint.ui.presenter.ProdukPresenter;
import com.rplsukses.ezprint.ui.view.MitraView;
import com.rplsukses.ezprint.ui.view.ProdukView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadActivity extends AppCompatActivity implements ProdukView, MitraView {
    private ProdukPresenter produkPresenter;
    private MitraPresenter mitraPresenter;
    private Mitra mitraActive;
    private Produk produkActive;
    private int idProduk, idMitra;

    @BindView(R.id.activity_upload_toolbar)Toolbar mToolbar;
    @BindView(R.id.activity_upload_tvTitle) TextView mTvTitle;
    @BindView(R.id.activity_upload_tvDetail) TextView mTvDetail;
    @BindView(R.id.activity_upload_tvMitra) TextView mTvMitra;
    @BindView(R.id.activity_upload_tvHarga) TextView mTvHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        initPresenter();
        init();
    }

    private void init(){
        idProduk = getIntent().getIntExtra("idProduk", 0);
        idMitra = getIntent().getIntExtra("idMitra", 0);

        produkActive = produkPresenter.getProdukByID(idProduk);
        mitraActive = mitraPresenter.getByID(idMitra);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mTvTitle.setText(produkActive.getKategori() + " : " + produkActive.getUkuran());
        mTvDetail.setText("Bahan : " + produkActive.getBahan() + "\nWarna : " + produkActive.getWarna());
        mTvMitra.setText(mitraActive.getNama());
        mTvHarga.setText(produkActive.getHarga().toString());
    }

    private void initPresenter(){
        produkPresenter = new ProdukPresenter(this);
        mitraPresenter = new MitraPresenter(this);
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
    public void loadMitra(List<Mitra> mitraList) {

    }

    @Override
    public void loadProduk(List<Produk> produkList) {

    }
}
