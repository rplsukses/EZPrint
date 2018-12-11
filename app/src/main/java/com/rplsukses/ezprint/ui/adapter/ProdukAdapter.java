package com.rplsukses.ezprint.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Produk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder>{
    private List<Produk> produkList = new ArrayList<>();
    Context ctx;
    int flag = 0;

    public ProdukAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public ProdukAdapter(Context ctx, int flag) {
        this.ctx = ctx;
        this.flag = flag;
    }

    public ProdukAdapter(List<Produk> produkList) {
        this.produkList = produkList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(produkList.get(position).getKategori() + " : " + produkList.get(position).getUkuran());
    }

    @Override
    public int getItemCount() {
        return produkList.size() ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_produk_tvTitle)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void generate(List<Produk> produkList) {
        clear();
        this.produkList = produkList;
        notifyDataSetChanged();
    }

    public void clear() {
        produkList.clear();
        notifyDataSetChanged();
    }
}

