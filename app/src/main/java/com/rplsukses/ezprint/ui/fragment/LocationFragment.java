package com.rplsukses.ezprint.ui.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.ui.adapter.MitraAdapter;
import com.rplsukses.ezprint.ui.presenter.MitraPresenter;
import com.rplsukses.ezprint.ui.view.MitraView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment implements MitraView {
    private MitraAdapter mAdapter;
    private MitraPresenter mitraPresenter;
    private List<Mitra> mList = new ArrayList<>();


    @BindView(R.id.fragment_location_recyclerView)
    RecyclerView mRvContent;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);
        init();
        mitraPresenter = new MitraPresenter(this);

        return view;
    }

    public void init(){
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MitraAdapter(getActivity());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadMitra(List<Mitra> mitraList) {
        mAdapter.generate(mitraList);
        mRvContent.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mitraPresenter.loadData();
    }
}
