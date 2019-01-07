package com.rplsukses.ezprint.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.dao.MitraDao;
import com.rplsukses.ezprint.bl.db.dao.ProdukDao;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.bl.db.model.Transaksi;
import com.rplsukses.ezprint.bl.network.config.Config;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {
    private List<Transaksi> mTransaksiList = new ArrayList<>();
    private List<Mitra> mitraList = new ArrayList<>();
    Context ctx;

    public TransaksiAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public TransaksiAdapter(Context ctx, List<Mitra> mitraList) {
        this.mitraList = mitraList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mitra mitra = new Mitra();
        try {
            mitra = MitraDao.getMitraDao().getMitraByID(mTransaksiList.get(position).getId_mitra());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] file = mTransaksiList.get(position).getFile().split("/");
        String getStatus = mTransaksiList.get(position).getStatus();
        String status = (getStatus.equals("0")? "Belum di proses" : getStatus.equals("1")? "Dalam proses" : getStatus.equals("2")? "Selesai" : "Batal");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YY");
        Integer idProduk = mTransaksiList.get(position).getId_produk();
        Produk produk = new Produk();
        try {
            produk = ProdukDao.getProdukDao().readByID(idProduk);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        holder.tvMitra.setText(mitra.getNama());
        holder.tvStatus.setText(status);
        holder.tvTgl.setText(mTransaksiList.get(position).getTgl_pesan());
        holder.tvFile.setText(file[3]);
        Picasso.get().load(Config.API_ICON_KATEGORI + produk.getIcon()).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return mTransaksiList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_order_ivIcon)
        ImageView ivIcon;
        @BindView(R.id.item_order_tvFile)
        TextView tvFile;
        @BindView(R.id.item_order_tvMitra)
        TextView tvMitra;
        @BindView(R.id.item_order_tvstatus)
        TextView tvStatus;
        @BindView(R.id.item_order_tvTgl)
        TextView tvTgl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_order_cardView) public void onClick(){
            Toast.makeText(ctx, tvFile.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void generate(List<Transaksi> list){
        clear();
        mTransaksiList = list;
        notifyDataSetChanged();
    }

    public void clear(){
        mTransaksiList.clear();
        notifyDataSetChanged();
    }
}
