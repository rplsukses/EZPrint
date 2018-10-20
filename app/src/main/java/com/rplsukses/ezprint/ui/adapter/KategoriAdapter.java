package com.rplsukses.ezprint.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.model.Kategori;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder>{
    private List<Kategori> mKategoriList = new ArrayList<>();
    Context ctx;

    public KategoriAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(mKategoriList.get(position).getKategori());
    }

    @Override
    public int getItemCount() {
        return mKategoriList.size() ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_kategori_tvKategori)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_kategori_cardView) public void onClick(){
            MaterialDialog.Builder dialog = new MaterialDialog.Builder(ctx)
                    .title("Ukuran Kertas")
                    .items(R.array.list_kertas);
            dialog.itemsCallback(new MaterialDialog.ListCallback() {
                @Override
                public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                    //Intent intent = new Intent(ctx, OrderActivity.class);
                    //ctx.startActivity(intent);
                }
            });

            dialog.show();
        }
    }

    public void generate(List<Kategori> kategoriList) {
        clear();
        this.mKategoriList = kategoriList;
        notifyDataSetChanged();
    }

    public void clear() {
        mKategoriList.clear();
        notifyDataSetChanged();
    }
}
