package com.example.ricardosgobbe.manager.controlers.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ricardosgobbe.manager.R;
import com.example.ricardosgobbe.manager.controlers.adapters.RecyclerAdapterProfile;

/**
 * Created by ricardo.sgobbe on 14/03/2016.
 */
public class FragmentProfile extends Fragment {

    private RecyclerView mRecycler;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_profile, container, false);
        mRecycler = (RecyclerView) mView.findViewById(R.id.id_profile_recycler);
        mRecycler.setAdapter(new RecyclerAdapterProfile());
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return mView;
    }


    public void fadeFrag(){
            mView.animate().alpha(0).setDuration(android.R.integer.config_longAnimTime).start();
    }
}
