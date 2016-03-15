package com.example.ricardosgobbe.manager.controlers.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricardosgobbe.manager.R;
import com.example.ricardosgobbe.manager.controlers.fragment.FragmentProfile;
import com.example.ricardosgobbe.manager.controlers.fragment.FragmentTest;

/**
 * Created by ricardo.sgobbe on 08/03/2016.
 */
public class MainActivity extends AppCompatActivity {
    private TabLayout tab;
    private int mImages[];
    private ViewPager mPager;
    private ImageView mIcon;
    private CardView mCardview;
    private TextView mTextView;
    private boolean mClick;
    private FragmentTest mTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //Pega referencia dos componentes
        bindComponents();
        //seta as configuracoes do pager
        configPager();
        //seta as configuracoes da tab
        configTab();
        //seta Listener no imageView responsavel por mostrar a barra de pesquisa
        setListenerOnView();

    }

    private void configTab() {
        tab.setupWithViewPager(mPager);
        tab.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        //Primeira tab não tem barra de pesquisa. Esses comandos impedem que ela apareça
                        if (tab.getPosition() == 0) {
                            mIcon.setVisibility(View.INVISIBLE);
                            mCardview.setVisibility(View.INVISIBLE);
                            if (mClick) {
                                reverseAnimation();
                                mClick = false;
                            }
                        } else {
                            mIcon.setVisibility(View.VISIBLE);
                        }
                        mPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                }
        );
        //seta icones nas tabs
        for (int i = 0; i < tab.getTabCount(); i++) {
            tab.getTabAt(i).setIcon(mImages[i]);
        }

    }

    private void configPager() {
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        mTest = new FragmentTest();
                        return mTest;
                    default:
                        return new FragmentProfile();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    private void bindComponents() {
        tab = (TabLayout) findViewById(R.id.id_tab_main);
        mPager = (ViewPager) findViewById(R.id.id_main_viewpager);
        mIcon = (ImageView) findViewById(R.id.id_imageview_main);
        mTextView = (TextView) findViewById(R.id.id_main_textview);
        mCardview = (CardView) findViewById(R.id.id_main_cardview);
        mImages = new int[]{R.drawable.ic_public_white_24dp, R.drawable.ic_find_in_page_white_24dp, R.drawable.ic_group_white_24dp};

    }


    private void setListenerOnView() {
        mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mClick) {
                    startAnimationOnViews();
                    mClick = true;
                } else {
                    reverseAnimation();
                    mClick = false;
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void reverseAnimation() {
        ObjectAnimator reverseTextView = ObjectAnimator.ofFloat(mTextView, "x", -130, mTextView.getMinimumWidth() + getResources().getDimension(R.dimen.margin_left));
        reverseTextView.setDuration(1200);

        ObjectAnimator visibilityAnimator = ObjectAnimator.ofInt(mCardview, "visibility", View.VISIBLE, View.INVISIBLE);
        visibilityAnimator.setDuration(1000);

        Animator reverseCircularRev = ViewAnimationUtils.createCircularReveal(mCardview, mCardview.getWidth(), mCardview.getHeight() / 2, mCardview.getWidth(), 0);
        reverseCircularRev.setDuration(1000);
        reverseTextView.start();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(reverseCircularRev).with(visibilityAnimator);
        animatorSet.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startAnimationOnViews() {
        mCardview.setVisibility(View.VISIBLE);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mCardview, mCardview.getWidth(), mCardview.getHeight() / 2,
                0, mCardview.getWidth());
        circularReveal.setDuration(1500);
        circularReveal.setInterpolator(new BounceInterpolator());
        circularReveal.start();

        ObjectAnimator textViewAnimation = ObjectAnimator.ofFloat(mTextView, "x", mTextView.getMinimumWidth() + getResources().getDimension(R.dimen.margin_left), -130);
        textViewAnimation.setDuration(1400);
        textViewAnimation.setInterpolator(new BounceInterpolator());
        textViewAnimation.start();


    }


}
