package com.example.weidu_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weidu_app.R;
import com.example.weidu_app.myadd.CityListActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FiveFragment extends Fragment {
    @BindView(R.id.my_text_content)
    TextView myTextContent;
    @BindView(R.id.myPerson)
    TextView myPerson;
    @BindView(R.id.my_personal_data)
    LinearLayout myPersonalData;
    @BindView(R.id.myCircle)
    TextView myCircle;
    @BindView(R.id.my_group)
    LinearLayout myGroup;
    @BindView(R.id.myFoot)
    TextView myFoot;
    @BindView(R.id.my_footprint)
    LinearLayout myFootprint;
    @BindView(R.id.myMoney)
    TextView myMoney;
    @BindView(R.id.my_wallet)
    LinearLayout myWallet;
    @BindView(R.id.myAddress)
    TextView myAddress;
    @BindView(R.id.my_harvesting_address)
    LinearLayout myHarvestingAddress;
    @BindView(R.id.relb)
    LinearLayout relb;
    @BindView(R.id.my_circle_ImageView)
    SimpleDraweeView myCircleImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.five_fragment, container, false);
        //黄油刀
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.myPerson, R.id.myCircle, R.id.my_circle, R.id.myFoot, R.id.myMoney, R.id.myAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myPerson:
                break;
            case R.id.myCircle:
                break;
            case R.id.my_circle:
                break;
            case R.id.myFoot:
                break;
            case R.id.myMoney:
                break;
            case R.id.myAddress:
                startActivity(new Intent(getActivity(),CityListActivity.class));
                break;
        }
    }
}
