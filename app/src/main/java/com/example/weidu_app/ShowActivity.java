package com.example.weidu_app;


import com.example.weidu_app.fragment.FiveFragment;
import com.example.weidu_app.fragment.FourFragment;
import com.example.weidu_app.fragment.OneFragment;
import com.example.weidu_app.fragment.ThreeFragment;
import com.example.weidu_app.fragment.TwoFragment;
import com.example.weidu_app.utils.BaseActivity;
import com.hjm.bottomtabbar.BottomTabBar;

public class ShowActivity extends BaseActivity {
    private BottomTabBar fragment;




    @Override
    protected void initData() {
        fragment.init(getSupportFragmentManager())
                .setImgSize(80,80)
                .setFontSize(0)
                .setTabPadding(80,6,0)
                .addTabItem("",R.drawable.home_s,R.drawable.home_n,OneFragment.class)
                .addTabItem("",R.drawable.circle_s,R.drawable.circle_n,TwoFragment.class)
                .setTabPadding(30,6,0)
                .setImgSize(120,120)
                .addTabItem("",R.drawable.gou_two,R.drawable.gou_two,ThreeFragment.class)
                .setImgSize(80,80)
                .setTabPadding(80,6,0)
                .addTabItem("",R.drawable.list_s,R.drawable.list_n,FourFragment.class)
                .addTabItem("",R.drawable.mine_s,R.drawable.mine_n,FiveFragment.class)
                .isShowDivider(false);
    }

    @Override
    protected void initView() {
        fragment = findViewById(R.id.fragment);
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_show;
    }
}
