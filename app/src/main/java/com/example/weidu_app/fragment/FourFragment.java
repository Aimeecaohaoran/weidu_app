package com.example.weidu_app.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.weidu_app.R;
import com.example.weidu_app.bean.DingDanBean;
import com.example.weidu_app.dingFragment.AllListFragment;
import com.example.weidu_app.dingFragment.CompletedFragment;
import com.example.weidu_app.dingFragment.WaitEvalueteFragment;
import com.example.weidu_app.dingFragment.WaitPayFragment;
import com.example.weidu_app.dingFragment.WaitReceiveFragment;
import com.example.weidu_app.dingdan.presenter.DingPresenter;
import com.example.weidu_app.dingdan.view.Idingview;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;


public class FourFragment extends Fragment{
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.list_wait_for_pay)
    TextView listWaitForPay;
    @BindView(R.id.list_wait_for_receive)
    TextView listWaitForReceive;
    @BindView(R.id.list_wait_for_evaluate)
    TextView listWaitForEvaluate;
    @BindView(R.id.list_completed)
    TextView listCompleted;
    @BindView(R.id.list_framelayout)
    ViewPager viewPager;
    @BindView(R.id.list_all_list)
    RadioButton listAllList;
    private DingPresenter dingPresenter;
    private AllListFragment allListFragment;
    private WaitPayFragment waitPayFragment;
    private WaitReceiveFragment waitReceiveFragment;
    private WaitEvalueteFragment waitEvalueteFragment;
    private ArrayList<Fragment> fragmentList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.four_fragment, container, false);
        //黄油刀
        ButterKnife.bind(this, view);
        fragmentList = new ArrayList<>();
        fragmentList.add(new AllListFragment());
        fragmentList.add(new WaitPayFragment());
        fragmentList.add(new WaitReceiveFragment());
        fragmentList.add(new WaitEvalueteFragment());
        fragmentList.add(new CompletedFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.list_all_list:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.list_wait_for_pay:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.list_wait_for_receive:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.list_wait_for_evaluate:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.list_completed:
                        viewPager.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });


        return view;
    }
}
