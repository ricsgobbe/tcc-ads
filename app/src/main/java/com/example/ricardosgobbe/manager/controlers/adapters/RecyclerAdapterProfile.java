package com.example.ricardosgobbe.manager.controlers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricardosgobbe.manager.R;


/**
 * Created by ricardo.sgobbe on 11/03/2016.
 */
public class RecyclerAdapterProfile extends RecyclerView.Adapter<RecyclerAdapterProfile.ProfileHolder> {

    private ImageView mImageView;
    private TextView mTitle;
    private TextView mSubtitle;

    @Override
    public ProfileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_line, parent, false);
        ProfileHolder profileHolder = new ProfileHolder(view);
        return profileHolder;
    }

    @Override
    public void onBindViewHolder(ProfileHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }


    public class ProfileHolder extends RecyclerView.ViewHolder {
        private View mView;
        public ProfileHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mImageView = (ImageView) mView.findViewById(R.id.id_profile_image);
            mTitle = (TextView) mView.findViewById(R.id.id_profile_title);
            mSubtitle = (TextView) mView.findViewById(R.id.id_profile_subtitle);

        }




    }
}
