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
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Transaksi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {
    private List<Transaksi> mTransaksiList = new ArrayList<>();
    Context ctx;

    public TransaksiAdapter(Context ctx) {
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
        holder.tvMitra.setText(mTransaksiList.get(position).getId_mitra().toString());
        holder.tvStatus.setText(mTransaksiList.get(position).getStatus());
        holder.tvTgl.setText(mTransaksiList.get(position).getTgl_pesan());
        holder.tvFile.setText(mTransaksiList.get(position).getFile());
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
