package com.rplsukses.ezprint.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Kategori;
import com.rplsukses.ezprint.bl.network.model.KategoriGet;
import com.rplsukses.ezprint.ui.adapter.KategoriAdapter;
import com.rplsukses.ezprint.ui.presenter.KategoriPresenter;
import com.rplsukses.ezprint.ui.view.KategoriView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriFragment extends Fragment implements KategoriView {
    private KategoriAdapter mAdapter;
    private KategoriPresenter kategoriPresenter;
    private List<Kategori> mList = new ArrayList<>();

    @BindView(R.id.fragment_kategori_recyclerView)
    RecyclerView mRvContent;

    public KategoriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init(){
        kategoriPresenter = new KategoriPresenter(this);
        mAdapter = new KategoriAdapter(getActivity());
        mRvContent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadItem(List<Kategori> kategoriList) {
        mAdapter.generate(kategoriList);
        mRvContent.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        kategoriPresenter.loadData();
    }
}
