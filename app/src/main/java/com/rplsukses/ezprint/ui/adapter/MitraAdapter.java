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
import com.rplsukses.ezprint.bl.model.Mitra;
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_mitra_tv)
        TextView tvTittle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_mitra_cardView) public void onClick(){
            if (flag == 0) {
                Intent intent = new Intent(ctx, MitraActivity.class);
                intent.putExtra("mitra", tvTittle.getText().toString());
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
