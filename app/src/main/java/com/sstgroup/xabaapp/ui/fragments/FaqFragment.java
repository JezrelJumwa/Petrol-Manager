package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.sstgroup.xabaapp.utils.Utils.collapse;
import static com.sstgroup.xabaapp.utils.Utils.expand;

public class FaqFragment extends BaseFragment {

    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.grp_1)
    LinearLayout grp1;

    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.grp_2)
    LinearLayout grp2;

    @BindView(R.id.img_3)
    ImageView img3;
    @BindView(R.id.grp_3)
    LinearLayout grp3;

    @BindView(R.id.img_4)
    ImageView img4;
    @BindView(R.id.grp_4)
    LinearLayout grp4;

    @BindView(R.id.img_5)
    ImageView img5;
    @BindView(R.id.grp_5)
    LinearLayout grp5;

    @BindView(R.id.img_6)
    ImageView img6;
    @BindView(R.id.grp_6)
    LinearLayout grp6;

    @BindView(R.id.img_7)
    ImageView img7;
    @BindView(R.id.grp_7)
    LinearLayout grp7;

    @BindView(R.id.img_8)
    ImageView img8;
    @BindView(R.id.grp_8)
    LinearLayout grp8;

    @BindView(R.id.img_9)
    ImageView img9;
    @BindView(R.id.grp_9)
    LinearLayout grp9;

    @BindView(R.id.img_10)
    ImageView img10;
    @BindView(R.id.grp_10)
    LinearLayout grp10;

    @BindView(R.id.img_11)
    ImageView img11;
    @BindView(R.id.grp_11)
    LinearLayout grp11;

    @BindView(R.id.img_12)
    ImageView img12;
    @BindView(R.id.grp_12)
    LinearLayout grp12;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_faq;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    public static FaqFragment newInstance() {

        Bundle args = new Bundle();

        FaqFragment fragment = new FaqFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.grp_title_1, R.id.grp_title_2, R.id.grp_title_3, R.id.grp_title_4,
            R.id.grp_title_5, R.id.grp_title_6, R.id.grp_title_7, R.id.grp_title_8,
            R.id.grp_title_9, R.id.grp_title_10, R.id.grp_title_11, R.id.grp_title_12})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.grp_title_1:

                if (grp1.getVisibility() == View.GONE) {
                    expand(grp1);
                    img1.setImageResource(R.drawable.close);
                } else {
                    collapse(grp1);
                    img1.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_2:

                if (grp2.getVisibility() == View.GONE) {
                    expand(grp2);
                    img2.setImageResource(R.drawable.close);
                } else {
                    collapse(grp2);
                    img2.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_3:

                if (grp3.getVisibility() == View.GONE) {
                    expand(grp3);
                    img3.setImageResource(R.drawable.close);
                } else {
                    collapse(grp3);
                    img3.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_4:

                if (grp4.getVisibility() == View.GONE) {
                    expand(grp4);
                    img4.setImageResource(R.drawable.close);
                } else {
                    collapse(grp4);
                    img4.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_5:

                if (grp5.getVisibility() == View.GONE) {
                    expand(grp5);
                    img5.setImageResource(R.drawable.close);
                } else {
                    collapse(grp5);
                    img5.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_6:

                if (grp6.getVisibility() == View.GONE) {
                    expand(grp6);
                    img6.setImageResource(R.drawable.close);
                } else {
                    collapse(grp6);
                    img6.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_7:

                if (grp7.getVisibility() == View.GONE) {
                    expand(grp7);
                    img7.setImageResource(R.drawable.close);
                } else {
                    collapse(grp7);
                    img7.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_8:

                if (grp8.getVisibility() == View.GONE) {
                    expand(grp8);
                    img8.setImageResource(R.drawable.close);
                } else {
                    collapse(grp8);
                    img8.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_9:

                if (grp9.getVisibility() == View.GONE) {
                    expand(grp9);
                    img9.setImageResource(R.drawable.close);
                } else {
                    collapse(grp9);
                    img9.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_10:

                if (grp10.getVisibility() == View.GONE) {
                    expand(grp10);
                    img10.setImageResource(R.drawable.close);
                } else {
                    collapse(grp10);
                    img10.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_11:

                if (grp11.getVisibility() == View.GONE) {
                    expand(grp11);
                    img11.setImageResource(R.drawable.close);
                } else {
                    collapse(grp11);
                    img11.setImageResource(R.drawable.open);
                }
                break;

            case R.id.grp_title_12:

                if (grp12.getVisibility() == View.GONE) {
                    expand(grp12);
                    img12.setImageResource(R.drawable.close);
                } else {
                    collapse(grp12);
                    img12.setImageResource(R.drawable.open);
                }
                break;
        }
    }

}
