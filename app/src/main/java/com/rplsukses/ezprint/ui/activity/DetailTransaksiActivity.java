package com.rplsukses.ezprint.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.dao.MitraDao;
import com.rplsukses.ezprint.bl.db.dao.ProdukDao;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.bl.db.model.Transaksi;
import com.rplsukses.ezprint.bl.network.config.Config;
import com.rplsukses.ezprint.ui.presenter.TransaksiPresenter;
import com.rplsukses.ezprint.ui.view.TransaksiView;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTransaksiActivity extends AppCompatActivity implements TransaksiView {
    TransaksiPresenter transaksiPresenter;
    Transaksi transaksiActive;
    Mitra mitra;
    Produk produk;
    Integer id_transaksi;

    @BindView(R.id.activity_detail_trans_toolbar) Toolbar mToolbar;
    @BindView(R.id.activity_detail_trans_ivKategori) ImageView mIvKategori;
    @BindView(R.id.activity_detail_trans_ivMitra) ImageView mIvMitra;
    @BindView(R.id.activity_detail_trans_tvBahan) TextView mTvBahan;
    @BindView(R.id.activity_detail_trans_tvFile) TextView mTvFile;
    @BindView(R.id.activity_detail_trans_tvHarga) TextView mTvHarga;
    @BindView(R.id.activity_detail_trans_tvHargaTotal) TextView mTvHargaTotal;
    @BindView(R.id.activity_detail_trans_tvWarna) TextView mTvWarna;
    @BindView(R.id.activity_detail_trans_tvUkuran) TextView mTvUkuran;
    @BindView(R.id.activity_detail_trans_tvMitra) TextView mTvMitra;
    @BindView(R.id.activity_detail_trans_tvKategori) TextView mTvKategori;
    @BindView(R.id.activity_detail_trans_tvJumlah) TextView mTvJumlah;
    @BindView(R.id.activity_detail_trans_tvAlamat) TextView mTvAlamat;
    @BindView(R.id.activity_detail_trans_tvJam) TextView mTvJamBuka;
    @BindView(R.id.activity_detail_trans_tvTelp) TextView mTvTelp;
    @BindView(R.id.activity_detail_trans_tvStatus) TextView mTvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        ButterKnife.bind(this);
        initData();
        initViews();
    }

    private void initData(){
        id_transaksi = getIntent().getIntExtra("id_transaksi", 0);
        transaksiPresenter = new TransaksiPresenter(this);
        transaksiActive = transaksiPresenter.getByID(id_transaksi);
        try {
            mitra = MitraDao.getMitraDao().getMitraByID(transaksiActive.getId_mitra());
            produk = ProdukDao.getProdukDao().readByID(transaksiActive.getId_produk());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initViews(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String getStatus = transaksiActive.getStatus();
        String status = (getStatus.equals("0")? "Belum di proses" : getStatus.equals("1")? "Dalam proses" : getStatus.equals("2")? "Selesai" : "Batal");

        mTvMitra.setText(mitra.getNama());
        mTvAlamat.setText(mitra.getAlamat());
        mTvJamBuka.setText(mitra.getJam_buka() + " - " + mitra.getJam_tutup());
        mTvTelp.setText(mitra.getTelepon());
        mTvKategori.setText("Kategori : " + produk.getKategori());
        mTvFile.setText(transaksiActive.getFile());
        mTvBahan.setText("Bahan : " + produk.getBahan());
        mTvUkuran.setText("Ukuran : " + produk.getUkuran());
        mTvWarna.setText("Warna : " + produk.getWarna());
        mTvHarga.setText("Harga satuan : " + produk.getHarga().toString());
        mTvJumlah.setText("Jumlah : " + transaksiActive.getJumlah().toString());
        mTvHargaTotal.setText("Harga total : " + produk.getHarga().multiply(BigDecimal.valueOf(transaksiActive.getJumlah())).toString());
        mTvStatus.setText(status);
        Picasso.get().load(Config.API_ICON_MITRA + mitra.getFoto()).into(mIvMitra);
        Picasso.get().load(Config.API_ICON_KATEGORI + produk.getIcon()).into(mIvKategori);
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
    public void loadProduk(List<Transaksi> transaksiList) {

    }
}
