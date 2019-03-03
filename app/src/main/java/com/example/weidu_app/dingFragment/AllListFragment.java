package com.example.weidu_app.dingFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weidu_app.R;
import com.example.weidu_app.adapter.StringAdapter;
import com.example.weidu_app.bean.DingDanBean;
import com.example.weidu_app.dingdan.bean.DingBean;
import com.example.weidu_app.dingdan.bean.OrderBean;
import com.example.weidu_app.dingdan.presenter.DingPresenter;
import com.example.weidu_app.dingdan.view.Idingview;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


public class AllListFragment extends Fragment implements Idingview {
    private DingPresenter dingPresenter;
    @BindView(R.id.allordersRecycle)
    RecyclerView allordersRecycle;
    private StringAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_list, container, false);
        ButterKnife.bind(this, view);
        dingPresenter = new DingPresenter(this);
        SharedPreferences userS = getActivity().getSharedPreferences("UserS", Context.MODE_PRIVATE);
        String sessionId = userS.getString("sessionId", "");
        String userId = userS.getString("userId", "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        final HashMap<String, Object> data = new HashMap<>();
        data.put("status", 0000);
        data.put("page", 1);
        data.put("count", 5);
        dingPresenter.getFindding(map,data);
        allordersRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        adapter=new StringAdapter(getActivity(),0);
        allordersRecycle.setAdapter(adapter);
        adapter.cancleListener(new StringAdapter.CancleListener() {
            @Override
            public void callBack(OrderBean.OrderListBean list) {
                Map<String,String> map=new HashMap<>();
                map.put("orderId",list.getOrderId());
                // persenter.sendMessageDelete(Constant.DELORDER,map,RegBean.class);
            }
        });


        adapter.setShopCarListener(new StringAdapter.ShopCarListener() {
            @Override
            public void callBack(OrderBean.OrderListBean list) {
                String orderStatus = list.getOrderStatus();
                int i = Integer.parseInt(orderStatus);
                switch (i){
                    case 1:
                        Map<String,String> map=new HashMap<>();
                        map.put("orderId",list.getOrderId());
                        map.put("payType",1+"");
                        // data.sendMessage(Constant.ZHIFU,map,RegBean.class);
                        break;
                    case 2:
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("orderId", list.getOrderId());
                        //  persenter.onPutStartRequest(Constant.SHOUHUO,map1,RegBean.class);
                        break;
                    case 3:
                        Map<String, String> map3 = new HashMap<>();
                        map3.put("orderId", list.getOrderId());
                        map3.put("payType", 1 + "");
                        // persenter.sendMessage(Constant.ZHIFU, map3, RegBean.class);
                        break;
                    case 9:
                        break;
                    default:
                        break;
                }


            }
        });
        return view;
    }

    @Override
    public void getdvdata(Object object) {

    }

    @Override
    public void getfingding(Object object) {
    if (object!=null){
        OrderBean orderBean= (OrderBean) object;
        Toast.makeText(getActivity(),orderBean.getMessage(),Toast.LENGTH_SHORT).show();
        adapter.setData(orderBean.getOrderList());
    }
    }
}
