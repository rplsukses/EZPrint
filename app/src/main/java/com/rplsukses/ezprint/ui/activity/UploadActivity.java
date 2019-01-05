package com.rplsukses.ezprint.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.bl.network.api.Api;
import com.rplsukses.ezprint.bl.network.config.RetrofitBuilder;
import com.rplsukses.ezprint.bl.network.model.BaseRespons;
import com.rplsukses.ezprint.bl.network.model.User;
import com.rplsukses.ezprint.bl.util.PrefUtil;
import com.rplsukses.ezprint.ui.dialog.DialogBuilder;
import com.rplsukses.ezprint.ui.presenter.MitraPresenter;
import com.rplsukses.ezprint.ui.presenter.ProdukPresenter;
import com.rplsukses.ezprint.ui.util.RealPathUtil;
import com.rplsukses.ezprint.ui.view.MitraView;
import com.rplsukses.ezprint.ui.view.ProdukView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity implements ProdukView, MitraView {
    public static final int PICK_FILE = 100;
    private MaterialDialog dialog;
    private ProdukPresenter produkPresenter;
    private MitraPresenter mitraPresenter;
    private Mitra mitraActive;
    private Produk produkActive;
    private int idProduk, idMitra;
    private Api mApi;
    private User user;
    private Uri selectedFile;

    @BindView(R.id.activity_upload_toolbar)Toolbar mToolbar;
    @BindView(R.id.activity_upload_tvTitle) TextView mTvTitle;
    @BindView(R.id.activity_upload_tvDetail) TextView mTvDetail;
    @BindView(R.id.activity_upload_tvMitra) TextView mTvMitra;
    @BindView(R.id.activity_upload_tvHarga) TextView mTvHarga;
    @BindView(R.id.activity_upload_etFile) EditText mEtFile;
    @BindView(R.id.activity_upload_etKeterangan) EditText mEtKeterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        mApi = RetrofitBuilder.builder(this).create(Api.class);
        user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
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

    private boolean validate(){
        if (mEtFile.getText().toString() == null){
            return false;
        }
        return true;
    }

    private void uploadFile(Uri fileUri){
        showLoading();
        File file = new File(RealPathUtil.getRealPath(getApplication(), fileUri));
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
        MultipartBody.Part reqFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        mApi.uploadFile(user.getId_user(), idMitra, idProduk, filename, reqFile, mEtKeterangan.getText().toString()).enqueue(new Callback<BaseRespons>() {
            @Override
            public void onResponse(Call<BaseRespons> call, Response<BaseRespons> response) {
                BaseRespons respons = response.body();
                Log.i("UPLOAD_FILE", response.message());

                if (respons != null) {
                    if (!respons.getError()) {
                        Toast.makeText(getApplicationContext(), respons.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseRespons> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("UPLOAD_FILE", t.getMessage());
            }
        });
        hideLoading();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.activity_upload_btnNext) public void checkout(){
        if (validate()) {
            uploadFile(selectedFile);
        }
    }

    @OnClick(R.id.activity_upload_btnBrowse) public void selectFile(){
        // check if kategori == dokumen
        if(produkActive.getId_kategori() == 1) {
            String[] mimeTypes =
                    {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                            "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                            "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                            "text/plain",
                            "application/pdf"};

            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            for (String type: mimeTypes) {
                i.setType(type);
            }
            if (mimeTypes.length > 0) {
                i.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
            startActivityForResult(i, PICK_FILE);
        }else {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, PICK_FILE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE && resultCode == RESULT_OK && data != null) {
            selectedFile = data.getData();
            mEtFile.setText(new File(RealPathUtil.getRealPath(getApplicationContext(), selectedFile)).getName());
        }
    }

    @Override
    public void showLoading() {
        //dialog = DialogBuilder.showLoadingDialog(getApplicationContext(), "Updating Data", "Please wait..", false);
    }

    @Override
    public void hideLoading() {
        //dialog.dismiss();
    }

    @Override
    public void loadMitra(List<Mitra> mitraList) {

    }

    @Override
    public void loadProduk(List<Produk> produkList) {

    }
}
