package com.example.ricardosgobbe.manager.controlers.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ricardosgobbe.manager.R;
import com.example.ricardosgobbe.manager.controlers.adapters.RecyclerAdapterProfile;

/**
 * Created by ricardo.sgobbe on 11/03/2016.
 */
public class ActivityProfiles extends AppCompatActivity {

    private RecyclerView mRecycler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);
        mRecycler = (RecyclerView) findViewById(R.id.id_profile_recycler);
        mRecycler.setAdapter(new RecyclerAdapterProfile());
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
