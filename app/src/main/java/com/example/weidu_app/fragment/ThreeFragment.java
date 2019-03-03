package com.example.weidu_app.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.weidu_app.R;
import com.example.weidu_app.adapter.ShoppingAdapter;
import com.example.weidu_app.bean.AddShoppingCar;
import com.example.weidu_app.bean.Shopping;
import com.example.weidu_app.dingdan.ShoppingSubmitActivity;
import com.example.weidu_app.xiang.presenter.MyPresenter;
import com.example.weidu_app.xiang.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ThreeFragment extends Fragment implements IView {
    @BindView(R.id.shop_view)
    RecyclerView shopView;
    @BindView(R.id.iv_cricle)
    CheckBox ivCricle;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.layout_all)
    RelativeLayout layoutAll;
    @BindView(R.id.total_price)
    TextView totalPrice;
    @BindView(R.id.total_num)
    TextView totalNum;
    @BindView(R.id.sum_price)
    RelativeLayout sumPrice;
    @BindView(R.id.layout_buttom)
    RelativeLayout layoutButtom;
    Unbinder unbinder;
    private MyPresenter myPresenter;
    private HashMap<String, String> path;
    private LinearLayoutManager manager;
    private  List<Shopping.ResultBean> resultSum;
    private ArrayList<Shopping.ResultBean> checkedResult=new ArrayList<>();
    private ShoppingAdapter shoppingAdapter;
    private int checkSum;
    private int size;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.three_fragment, container, false);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this,view);
        //设置全选
        ivCricle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Shopping.ResultBean> result = shoppingAdapter.getResult();
                if (result!=null) {
                    for (int i = 0; i < result.size(); i++) {
                        if (ivCricle.isChecked()){
                            result.get(i).setChecked(true);
                        }
                        else {
                            result.get(i).setChecked(false);
                        }
                    }
                    shoppingAdapter.notifyDataSetChanged();
                    //全选更新价格
                    updataPriceSum();
                }
            }
        });
        //跳转提交订单的activity
        totalNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到选中的数据
                checkedResult.clear();
                List<Shopping.ResultBean> result = shoppingAdapter.getResult();
                for (int i = 0; i < result.size(); i++) {
                    Shopping.ResultBean resultBean = result.get(i);
                    //选中的话添加进去
                    if (resultBean.isChecked()){
                        checkedResult.add(resultBean);
                    }
                }
                //如果有商品选中则跳转
                if (checkedResult.size()>0){
                    Intent intent=new Intent(getActivity(),ShoppingSubmitActivity.class);
                    intent.putExtra("result", (Serializable) checkedResult);
                    intent.putExtra("sumsize",checkedResult.size());
                    intent.putExtra("sumprice",totalPrice.getText().toString().trim());
                    startActivityForResult(intent,0);

                }
                //如果没有商品选中则吐司
                else {
                    Toast.makeText(getActivity(), "请至少选择一个商品！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册方法
        // EventBus.getDefault().register(this);
        myPresenter = new MyPresenter(this);
        //创建布局管理器
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(OrientationHelper.VERTICAL);
        shopView.setLayoutManager(manager);
        SharedPreferences userS = getActivity().getSharedPreferences("UserS", Context.MODE_PRIVATE);
        String sessionId = userS.getString("sessionId", "");
        String userId = userS.getString("userId", "");
        path = new HashMap<>();
        path.put("sessionId",sessionId);
        path.put("userId",userId);
        myPresenter.getShopModelView(path);
    }

    @Override
    public void getXiangData(Object obj) {

    }

    @Override
    public void getAddShopData(Object obj) {

    }

    @Override
    public void getShopData(Object obj) {
        Shopping shopping = (Shopping) obj;
        if (shopping.getStatus().equals("0000")) {
            resultSum = new ArrayList<Shopping.ResultBean>();
            List<Shopping.ResultBean> result = shopping.getResult();
            for (int i = 0; i < result.size(); i++) {
                result.get(i).setChecked(false);
            }
            resultSum.addAll(result);
            shopView.setLayoutManager(new LinearLayoutManager(getActivity()));
            shoppingAdapter = new ShoppingAdapter(resultSum, getActivity());
            shopView.setAdapter(shoppingAdapter);
            //条目多选框接口回调
            shoppingAdapter.setItemCheckOnClickListner(new ShoppingAdapter.ItemCheckOnClickListner() {
                @Override
                public void click(int commodityId, boolean is) {
                    if (is) {
                        List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                        for (int i = 0; i < result1.size(); i++) {
                            int commodityId1 = result1.get(i).getCommodityId();
                            if (commodityId == commodityId1) {
                                result1.get(i).setChecked(is);
                            }
                        }
                        shoppingAdapter.notifyDataSetChanged();
                        //判断是否已经被动全选和更新价格
                        isAllChecked();
                    } else {
                        List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                        for (int i = 0; i < result1.size(); i++) {
                            int commodityId1 = result1.get(i).getCommodityId();
                            if (commodityId == commodityId1) {
                                result1.get(i).setChecked(is);
                            }
                        }
                        shoppingAdapter.notifyDataSetChanged();
                        //判断是否已经被动全选和更新价格
                        isAllChecked();
                    }
                }
            });

            //数量加接口回调
            shoppingAdapter.setItemJiaOnClickListner(new ShoppingAdapter.ItemJiaOnClickListner() {
                @Override
                public void click(int commodityId) {
                    List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                    for (int i = 0; i < result1.size(); i++) {
                        int commodityId1 = result1.get(i).getCommodityId();
                        if (commodityId == commodityId1) {
                            result1.get(i).setCount(result1.get(i).getCount() + 1);
                        }
                    }
                    shoppingAdapter.notifyDataSetChanged();
                    //计算价格
                    updataPriceSum();
                }
            });

            //数量减接口回调
            shoppingAdapter.setItemJianOnClickListner(new ShoppingAdapter.ItemJianOnClickListner() {
                @Override
                public void click(int commodityId) {
                    List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                    for (int i = 0; i < result1.size(); i++) {
                        int commodityId1 = result1.get(i).getCommodityId();
                        if (commodityId == commodityId1) {
                            if (result1.get(i).getCount() == 1) {
                                Toast.makeText(getActivity(),"不能再减了",Toast.LENGTH_SHORT).show();
                                result1.get(i).setCount(1);
                            } else {
                                result1.get(i).setCount(result1.get(i).getCount() - 1);
                            }
                        }
                    }
                    shoppingAdapter.notifyDataSetChanged();
                    //计算价格
                    updataPriceSum();
                }
            });
            //删除购物车j
            shoppingAdapter.setItemDeleteOnClickListner(new ShoppingAdapter.ItemDeleteOnClickListner() {
                @Override
                public void click(int commodityId) {
                    List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                    result1.remove(commodityId);
                    shoppingAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "" + shopping.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getShopDatas(Object obj) {

    }

    //判断商品是否全部选中从而选中多选框
    public void isAllChecked(){
        checkSum=0;
        size=0;
        List<Shopping.ResultBean> result = shoppingAdapter.getResult();
        for (int i = 0; i < result.size(); i++) {
            size++;
            boolean checked = result.get(i).isChecked();
            if (checked){
                checkSum++;
            }
        }
        //如果集合中条目的数量和选中的数量相等的话 全选框设置
        if (checkSum==size){
            ivCricle.setChecked(true);
        }
        else {
            ivCricle.setChecked(false);
        }
        //并且更新总价
        updataPriceSum();
    }
    //更新总价
    public void updataPriceSum(){
        int priceSum = 0;
        List<Shopping.ResultBean> result = shoppingAdapter.getResult();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).isChecked()){
                int count = result.get(i).getCount();
                int price = result.get(i).getPrice();
                priceSum+=count*price;
            }
        }
        totalPrice.setText(""+priceSum);
    }
    //刷新购物车接口
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setRefresh(AddShoppingCar addShoppingCar){
        myPresenter.getShopModelView(path);
    }

}


