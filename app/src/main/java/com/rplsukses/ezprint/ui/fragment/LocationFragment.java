package com.rplsukses.ezprint.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.ui.adapter.MitraAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {
    private MitraAdapter mAdapter;
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
        initData();

        return view;
    }

    public void init(){
        mAdapter = new MitraAdapter(getActivity());
        mAdapter.generate(mList);
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setAdapter(mAdapter);
    }

    public void initData(){
        String[] mitra = getResources().getStringArray(R.array.list_mitra);
        for (String s : mitra){
            mList.add(new Mitra(s));
        }
    }

}
