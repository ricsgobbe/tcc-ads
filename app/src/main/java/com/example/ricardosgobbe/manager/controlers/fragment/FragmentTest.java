package com.example.ricardosgobbe.manager.controlers.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ricardosgobbe.manager.R;

/**
 * Created by ricardo.sgobbe on 03/02/2016.
 */
public class FragmentTest extends Fragment {

    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.test_fragment, container, false);
        return mView;
    }


    public void onFadeFragment(){
        mView.animate().alpha(0).setDuration(android.R.integer.config_longAnimTime).start();
    }

}
