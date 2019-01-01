package com.rplsukses.ezprint.ui.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.ui.activity.UploadActivity;
import com.rplsukses.ezprint.ui.activity.MitraActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MitraAdapter extends RecyclerView.Adapter<MitraAdapter.ViewHolder>{
    private List<Mitra> mList = new ArrayList<>();
    Context ctx;
    int flag = 0;

    public MitraAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public MitraAdapter(Context ctx, int flag) {
        this.ctx = ctx;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mitra, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTittle.setText(mList.get(position).getNama());
        holder.tvAlamat.setText(mList.get(position).getAlamat());
        holder.tvJam.setText(mList.get(position).getJam_buka() + " - " + mList.get(position).getJam_tutup());
        holder.id_mitra = mList.get(position).getId_mitra().toString();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_mitra_tvNama)
        TextView tvTittle;
        @BindView(R.id.item_mitra_ivIcon)
        ImageView ivIcon;
        @BindView(R.id.item_mitra_tvAlamat)
        TextView tvAlamat;
        @BindView(R.id.item_mitra_tvJam)
        TextView tvJam;
        private String id_mitra;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_mitra_cardView) public void onClick(){
            if (flag == 0) {
                Intent intent = new Intent(ctx, MitraActivity.class);
                intent.putExtra("id_mitra", id_mitra);
                ctx.startActivity(intent);
            }else {
                Intent intent = new Intent(ctx, UploadActivity.class);
                ctx.startActivity(intent);
            }
        }
    }

    public void generate(List<Mitra> list) {
        clear();
        this.mList = list;
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
