package com.example.ricardosgobbe.manager.controlers.custom;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ricardosgobbe.manager.R;

/**
 * Created by ricardo.sgobbe on 14/03/2016.
 */
public class FabMenu extends FloatingActionButton {
    private View mView;
    private FloatingActionButton mFab;


    public FabMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        mFab = (FloatingActionButton) inflate(context, R.layout.layout_fab_menu, new LinearLayout(getContext()));
        mFab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Teste", Toast.LENGTH_LONG).show();
            }
        });
    }
}
