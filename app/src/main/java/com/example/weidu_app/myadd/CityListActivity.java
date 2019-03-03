package com.example.weidu_app.myadd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidu_app.R;
import com.example.weidu_app.adapter.AddrAdapter;
import com.example.weidu_app.adapter.CityAdapter;
import com.example.weidu_app.adapter.ShoppingAdapter;
import com.example.weidu_app.bean.AddShoppingCar;
import com.example.weidu_app.bean.EventBean;
import com.example.weidu_app.bean.Shopping;
import com.example.weidu_app.bean.SyncBean;
import com.example.weidu_app.myadd.presenter.DingPresenter;
import com.example.weidu_app.myadd.view.Idingview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityListActivity extends AppCompatActivity implements Idingview {

    @BindView(R.id.my_child_address_complet)
    TextView addresscomelet;
    @BindView(R.id.my_child_address_ryl)
    RecyclerView addrecy;
    @BindView(R.id.my_child_address_addaddress)
    TextView add_addres;
    private DingPresenter persenter;
    private AddrAdapter addrAdapter;
    private HashMap<String, String> path;
    private AddressListBean addressListBean;
    private  List<ShouHuoBean.ResultBean> resultBean;
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        SharedPreferences userS = getSharedPreferences("UserS", Context.MODE_PRIVATE);
        String sessionId = userS.getString("sessionId", "");
        String userId = userS.getString("userId", "");
        path = new HashMap<>();
        path.put("sessionId",sessionId);
        path.put("userId",userId);
        persenter = new DingPresenter(this);
        add_addres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CityListActivity.this,CityAddActivity.class));
            }
        });
    }

    @Override
    public void getmyaddress(Object obj) {
        if (obj != null) {
            addressListBean = (AddressListBean) obj;
            addrAdapter=new AddrAdapter(CityListActivity.this);
            final LinearLayoutManager manager=new LinearLayoutManager(CityListActivity.this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            addrecy.setLayoutManager(manager);
            addrecy.setAdapter(addrAdapter);
            addrAdapter.setList(addressListBean.getResult());
            addrAdapter.setAddrClickListener(new AddrAdapter.AddrClickListener() {
                @Override
                public void callBack(int i) {
                    Map<String,String> map=new HashMap<>();
                    map.put("id",addressListBean.getResult().get(i).getId());
                   // persenter.sendMessage(Constant.MORENDIZHI,map,RegBean.class);
                }
            });

            addrAdapter.setChangeClickListener(new AddrAdapter.ChangeClickListener() {
                @Override
                public void callBack(final int i) {
                    EventBus.getDefault().postSticky(new EventBean("addr",addressListBean.getResult().get(i)));
                   // Intent intent=new Intent(CityListActivity.this,ChangeAddrActivity.class);
                  //  startActivity(intent);
                    //finish();
                }
            });

            addrAdapter.delClickListener(new AddrAdapter.DelClickListener() {
                @Override
                public void callBack(int i) {
                    Toast.makeText(CityListActivity.this,"亲,不能删除地址呦",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public void getaddaddress(Object obj) {
    }
    //刷新购物车接口
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setRefresh(AddShoppingCar addShoppingCar){
        persenter.getmyaddress(path);
    }

    @Override
    protected void onResume() {
        super.onResume();
        persenter.getmyaddress(path);
    }
}