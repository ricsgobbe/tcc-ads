package com.example.ricardosgobbe.manager.controlers.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ricardosgobbe.manager.R;

/**
 * Created by ricardo.sgobbe on 11/02/2016.
 */
public class FragmentSearchText extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.text_fragment, container, false);
        return v;
    }
}
