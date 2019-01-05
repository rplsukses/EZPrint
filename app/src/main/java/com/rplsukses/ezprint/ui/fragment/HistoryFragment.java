package com.rplsukses.ezprint.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Transaksi;
import com.rplsukses.ezprint.ui.adapter.TransaksiAdapter;
import com.rplsukses.ezprint.ui.dialog.DialogBuilder;
import com.rplsukses.ezprint.ui.presenter.TransaksiPresenter;
import com.rplsukses.ezprint.ui.view.TransaksiView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements TransaksiView {
    private MaterialDialog dialog;
    private TransaksiAdapter transaksiAdapter;
    private TransaksiPresenter transaksiPresenter;
    private List<Transaksi> transaksiList = new ArrayList<>();

    @BindView(R.id.fragment_history_recyclerView)
    RecyclerView rvContent;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init(){
        transaksiPresenter = new TransaksiPresenter(this);
        rvContent.setLayoutManager( new LinearLayoutManager(getActivity()));
        transaksiAdapter = new TransaksiAdapter(getActivity());
    }

    @Override
    public void showLoading() {
        dialog = DialogBuilder.showLoadingDialog(getContext(), "Updating Data", "Please wait..", false);
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void loadProduk(List<Transaksi> transaksiList) {
        transaksiAdapter.generate(transaksiList);
        rvContent.setAdapter(transaksiAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        transaksiPresenter.getHistory();
    }
}
