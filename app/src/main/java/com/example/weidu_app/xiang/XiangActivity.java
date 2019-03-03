package com.example.weidu_app.xiang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weidu_app.R;
import com.example.weidu_app.bean.AddShoppingCar;
import com.example.weidu_app.bean.EventBusBean;
import com.example.weidu_app.bean.ShopResultBean;
import com.example.weidu_app.bean.Shopping;
import com.example.weidu_app.bean.SyncBean;
import com.example.weidu_app.bean.XqBean;
import com.example.weidu_app.xiang.presenter.MyPresenter;
import com.example.weidu_app.xiang.view.IView;
import com.example.weidu_app.xiang.view.MyAlert;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class XiangActivity extends AppCompatActivity implements IView {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.saleNum)
    TextView saleNum;
    @BindView(R.id.commodityName)
    TextView commodityName;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.shopAdd)
    ImageView shopAdd;
    @BindView(R.id.shopBuy)
    ImageView shopBuy;
    private MyPresenter myPresenter;
    private String id;
    private Unbinder bind;
    private HashMap<String, String> path;
    private MyAlert myDialog;
    private XqBean xqBean;
    private String string;
    private ArrayList<ShopResultBean> list=new ArrayList<>();
    private String sessionId;
    private String userId;
    private JSONArray jsonArray;
    int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        //黄油刀绑定
        bind = ButterKnife.bind(this);
        SharedPreferences userS = getSharedPreferences("UserS", Context.MODE_PRIVATE);
        sessionId = userS.getString("sessionId", "");
        userId = userS.getString("userId", "");
        path = new HashMap<>();
        path.put("sessionId",sessionId);
        path.put("userId",userId);
        //注册方法
        EventBus.getDefault().register(this);
        myPresenter = new MyPresenter(this);
        myPresenter.getXiangModelView(id);


    }

    //取消订阅,销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    //接收订阅 ,,参数是bean
    @Subscribe(sticky = true)
    public void event(EventBusBean eventBusBean) {
        id = eventBusBean.getId();
    }

    @Override
    @SuppressLint("JavascriptInterface")
    public void getXiangData(Object obj) {
        if (obj != null) {
            xqBean = (XqBean) obj;
            String details = xqBean.getResult().getDetails();
            String picture = xqBean.getResult().getPicture();
            String[] split = picture.split(",");
            List<String> list = Arrays.asList(split);
            WebSettings settings = webview.getSettings();
            settings.setJavaScriptEnabled(true);//支持JS
            String js = "<script type=\"text/javascript\">" +
                    "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                    "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                    "imgs[i].style.width = '100%';" +  // 宽度改为100%
                    "imgs[i].style.height = 'auto';" +
                    "}" +
                    "</script>";
            webview.loadDataWithBaseURL(null, details + js, "text/html", "utf-8", null);
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(list);
            banner.start();
            price.setText("￥" + xqBean.getResult().getPrice());
            commodityName.setText(xqBean.getResult().getCommodityName());
            weight.setText(xqBean.getResult().getWeight() + "kg");
        }
    }

    @Override
    public void getAddShopData(Object obj) {
        if (obj!=null) {
            SyncBean syncBean= (SyncBean) obj;
            Toast.makeText(XiangActivity.this,syncBean.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void getShopData(Object obj) {


    }

    @Override
    public void getShopDatas(Object obj) {
        if (obj!=null){
            Shopping shopping = (Shopping) obj;
            List<Shopping.ResultBean> result = shopping.getResult();
            if (result.size()==0){
                String data=jsonArray.toString();
                myPresenter.getAddShopModelView(path,data);
            }else {
                List<ShopResultBean> list = new ArrayList<>();
                for (Shopping.ResultBean resultBean : result) {
                    if (Integer.valueOf(id)==resultBean.getCommodityId()){
                        resultBean.setCount(resultBean.getCount()+1);
                        a=1;
                    }
                    list.add(new ShopResultBean(resultBean.getCommodityId(),resultBean.getCount()));
                }
                if (a==1){
                    a=0;
                }else {
                    list.add(new ShopResultBean(Integer.valueOf(id),1));
                }
                Gson gson = new Gson();
                String s = gson.toJson(list);
                myPresenter.getAddShopModelView(path,s);

            }

        }

    }

    @OnClick({R.id.shopAdd, R.id.shopBuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopAdd:
                try {

                    //相当于中括号
                    jsonArray = new JSONArray();
                    //相当于大括号
                    JSONObject jsonObject=null;
                    jsonObject=new JSONObject();
                    jsonObject.put("commodityId",id);
                    jsonObject.put("count",1);
                    jsonArray.put(jsonObject);
                    path = new HashMap<>();
                    path.put("sessionId",sessionId);
                    path.put("userId",userId);
                    myPresenter.getShopModelViews(path);
                    //传一个eventbusbean对象过去
                    EventBus.getDefault().post(new AddShoppingCar(Integer.valueOf(id),1));
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.shopBuy:
                break;
        }
    }
    //Banner
    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    }


