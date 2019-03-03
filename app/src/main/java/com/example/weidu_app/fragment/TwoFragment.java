package com.example.weidu_app.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weidu_app.QuanZi.presenter.QuanPresenter;
import com.example.weidu_app.QuanZi.view.IQuanView;
import com.example.weidu_app.R;
import com.example.weidu_app.adapter.CircleAdapter;
import com.example.weidu_app.bean.QuanBean;
import com.example.weidu_app.bean.RegBean;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TwoFragment extends Fragment implements IQuanView {
    Unbinder unbinder;
    @BindView(R.id.circle_recycler)
    XRecyclerView circleRecycler;
    private LinearLayoutManager linearLayoutManager;
    private String TAG = "SecondFragment";
    int page=1;
    private CircleAdapter circleAdapter;
    int count=10;
    private QuanBean quanBean;
    private QuanPresenter quanPresenter;
    private Map<String, String> path;
    private Map<String, String> pase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences userS = getActivity().getSharedPreferences("UserS", Context.MODE_PRIVATE);
        String sessionId = userS.getString("sessionId", "");
        String userId = userS.getString("userId", "");
        path = new HashMap<>();
        pase = new HashMap<>();
        path.put("sessionId",sessionId);
        path.put("userId",userId);
        //绑定persenter
        quanPresenter = new QuanPresenter(this);
        quanPresenter.getQuanModelView(page,10);
        //创建线性布局
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        circleRecycler.setLayoutManager(linearLayoutManager);
        circleRecycler.setPullRefreshEnabled(true);
        circleRecycler.setLoadingMoreEnabled(true);
        circleRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){


                    @Override
                    public void run() {
                        page=1;
                        quanPresenter.getQuanModelView(page,count);
                        circleAdapter.setList(quanBean.getResult());
                        circleRecycler.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        circleAdapter.addList(quanBean.getResult());
                        page++;
                        quanPresenter.getQuanModelView(page,count+5);
                        circleRecycler.refreshComplete();
                        circleRecycler.loadMoreComplete();
                        }

                },2000);
            }
        });


        return view;
    }
    @Override
    public void getQuanData(Object obj) {
        quanBean= (QuanBean) obj;
        List<QuanBean.ResultBean> circleBeanResult = quanBean.getResult();
        //创建适配器
        circleAdapter = new CircleAdapter(getActivity(), circleBeanResult);
        circleRecycler.setAdapter(circleAdapter);
        circleAdapter.setGreatClick(new CircleAdapter.GreatClick() {
            @Override
            public void click(int circleId, boolean isGreat) {
                if (isGreat){
                    pase.put("circleId",circleId+"");
                    quanPresenter.getmodeldianzan(path,pase);
                }else{
                   quanPresenter.getmodelquxiaodianzan(path,pase);
                }
                circleAdapter.notifyDataSetChanged();

            }
        });
    }


    @Override
    public void getDianzanView(Object obj) {
        if (obj!=null){
            RegBean regBean = (RegBean) obj;
            String message = regBean.getMessage();
            Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void getQuxiaoDianzanView(Object obj) {
        if (obj!=null){
            RegBean regBean = (RegBean) obj;
            String message = regBean.getMessage();
            Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();

        }
    }
}

