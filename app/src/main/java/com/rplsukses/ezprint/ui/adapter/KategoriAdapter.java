package com.rplsukses.ezprint.ui.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.network.model.Kategori;
import com.rplsukses.ezprint.ui.activity.KategoriMitraActivity;
import com.rplsukses.ezprint.ui.activity.UploadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder>{
    private List<Kategori> mKategoriList = new ArrayList<>();
    Context ctx;
    int flag = 0;

    public KategoriAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public KategoriAdapter(Context ctx, int flag) {
        this.ctx = ctx;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.tvTitle.setText(mKategoriList.get(position).getKategori());
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
                    .items(R.array.list_kertas)
                    .positiveText(R.string.pilih)
                    .negativeText(R.string.batal)
                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            return false;
                        }
                    })
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            super.onPositive(dialog);
                            if (flag == 0) {
                                Intent intent = new Intent(ctx, KategoriMitraActivity.class);
                                intent.putExtra("kategori", tvTitle.getText().toString());
                                ctx.startActivity(intent);
                            }else {
                                Intent intent = new Intent(ctx, UploadActivity.class);
                                intent.putExtra("kategori", tvTitle.getText().toString());
                                ctx.startActivity(intent);
                            }
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            super.onNegative(dialog);
                            dialog.dismiss();
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
