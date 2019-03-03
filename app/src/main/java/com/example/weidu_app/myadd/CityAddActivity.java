package com.example.weidu_app.myadd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidu_app.R;
import com.example.weidu_app.bean.AddShoppingCar;
import com.example.weidu_app.bean.Citybean;
import com.example.weidu_app.bean.KeyBean;
import com.example.weidu_app.bean.ShopCarAddBean;
import com.example.weidu_app.bean.ShopResultBean;
import com.example.weidu_app.bean.Shopping;
import com.example.weidu_app.bean.SyncBean;
import com.example.weidu_app.myadd.presenter.DingPresenter;
import com.example.weidu_app.myadd.view.Idingview;
import com.google.gson.Gson;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityAddActivity extends AppCompatActivity implements Idingview {


    @BindView(R.id.my_add_address_name)
    EditText myAddAddressName;
    @BindView(R.id.my_add_address_phone)
    EditText myAddAddressPhone;
    @BindView(R.id.my_add_address_address)
    TextView myAddAddressAddress;
    @BindView(R.id.my_add_address_address_detail)
    EditText shu;
    @BindView(R.id.my_add_address_postcode)
    EditText myAddAddressPostcode;
    @BindView(R.id.my_add_address_save)
    TextView save;
    private String name1;
    private DingPresenter persenter;
    private CityPicker cityPicker;
    private String sessionId;
    private String userId;
    private HashMap<String, String> path;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_add);
        persenter = new DingPresenter(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        name1 = intent.getStringExtra("name");
        SharedPreferences userS = getSharedPreferences("UserS", Context.MODE_PRIVATE);
        sessionId = userS.getString("sessionId", "");
        userId = userS.getString("userId", "");
        path = new HashMap<>();
        path.put("sessionId",sessionId);
        path.put("userId",userId);

    }
    private void initCityPicker() {
        cityPicker = new CityPicker.Builder(CityAddActivity.this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                String province = citySelected[0];
                String city = citySelected[1];
                String district = citySelected[2];
                String code = citySelected[3];
                myAddAddressAddress.setText(province + city + district);
                myAddAddressPostcode.setText(code);
            }

            @Override
            public void onCancel() {
            }
        });
    }

    @OnClick({R.id.my_add_address_address, R.id.my_add_address_save})
    public void onViewClicked(View view)  {
        switch (view.getId()) {
            case R.id.my_add_address_address:
                initCityPicker();
                cityPicker.show();
                break;
            case R.id.my_add_address_save:
                Map<String, String> map = new HashMap();
                map.put("realName", myAddAddressName.getText().toString());
                map.put("phone", myAddAddressPhone.getText().toString());
                map.put("address", myAddAddressAddress.getText().toString() + " " + shu.getText().toString());
                map.put("zipCode", myAddAddressPostcode.getText().toString());
                persenter.getaddaddressaddada(path,map);
                EventBus.getDefault().post(new AddShoppingCar(id,1));
                startActivity(new Intent(this, CityListActivity.class));
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (name1.equals("1")) {
               // startActivity(new Intent(this, TJDDActivity.class));
            } else {
                startActivity(new Intent(this, CityListActivity.class));
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getmyaddress(Object obj) {

    }

    @Override
    public void getaddaddress(Object obj) {
        if (obj!=null){
            SyncBean shopCarAddBean = (SyncBean) obj;
            Toast.makeText(this, shopCarAddBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}