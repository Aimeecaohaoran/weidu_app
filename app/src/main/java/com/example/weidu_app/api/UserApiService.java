package com.example.weidu_app.api;



import com.example.weidu_app.bean.AllOrder;
import com.example.weidu_app.bean.ByIdBean;
import com.example.weidu_app.bean.DingDanBean;
import com.example.weidu_app.bean.ErJi;
import com.example.weidu_app.bean.FindShop;
import com.example.weidu_app.bean.HomeBean;
import com.example.weidu_app.bean.LoginBean;
import com.example.weidu_app.bean.NewsBeanTwo;
import com.example.weidu_app.bean.QuanBean;
import com.example.weidu_app.bean.RegBean;
import com.example.weidu_app.bean.ShopResultBean;
import com.example.weidu_app.bean.Shopping;
import com.example.weidu_app.bean.ShowShoppingBean;
import com.example.weidu_app.bean.SouBean;
import com.example.weidu_app.bean.SyncBean;
import com.example.weidu_app.bean.TopLasBean;
import com.example.weidu_app.bean.XqBean;
import com.example.weidu_app.dingdan.bean.DingBean;
import com.example.weidu_app.dingdan.bean.OrderBean;
import com.example.weidu_app.myadd.AddressListBean;
import com.example.weidu_app.myadd.ShouHuoBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface UserApiService {

    //首页
    @GET(ApiService.SHOP_LIE)
    Observable<HomeBean> getcommodity(@Query("page") int page);
    //XBanner
    @GET(ApiService.BANNER)
    Observable<NewsBeanTwo> getBanner();
    //登录
    @POST(ApiService.LOGIN)
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("phone") String phone, @Field("pwd") String pwd);
    //注册
    @POST(ApiService.REG_URL)
    @FormUrlEncoded
    Observable<RegBean> getReg(@Field("phone") String phone, @Field("pwd") String pwd);
    //圈子
    @GET(ApiService.SHOP_CIRCLE)
    Observable<QuanBean> getQuan(@Query("page") int page, @Query("count") int count);
    //商品详情
    @GET(ApiService.XIANG)
    Observable<XqBean> getXiang(@Query("commodityId") String commodityId);
    //首页搜索
    @GET(ApiService.SOUSUO)
    Observable<SouBean> getSou(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);
    //商品一级列表
    @GET(ApiService.YIJI)
    Observable<TopLasBean> getyiji();
    //商品二级列表
    @GET(ApiService.ERJI)
    Observable<ErJi> geterji(@Query("firstCategoryId") String firstCategoryId);
    //二级列表的商品
    @GET(ApiService.ERSHOP)
    Observable<ByIdBean> geterjishop(@Query("categoryId") String categoryId, @Query("page") int page, @Query("count") int count);
   //点赞
    @POST(ApiService.DIANZAN)
    @FormUrlEncoded
    Observable<RegBean> getDian(@HeaderMap Map<String, String> path, @FieldMap Map<String, String> pase);
    //取消点赞
    @DELETE(ApiService.QUXIAODIANZAN)
    Observable<RegBean> getXiaoDian(@HeaderMap Map<String, String> path, @QueryMap Map<String, String> pase);

    //查询购物车数据
    @GET(ApiService.CHASHOP)
    Observable<Shopping> getShop(@HeaderMap Map<String, String> path);
    //添加到购物车数据
    @PUT(ApiService.JIARUSHOP)
    @FormUrlEncoded
    Observable<SyncBean> getAddShop(@HeaderMap Map<String, String> path, @Field("data")String data);

    //创建订单
    @GET("small/order/verify/v1/createOrder")
    Observable<DingDanBean> getDingdan(@HeaderMap HashMap<String,Object> hashmap, @Field("addressId")int addressId, @Field("totalPrice")double totalPrice, @FieldMap HashMap<String,Object> ordermap);

    //查询收货地址
    @GET(ApiService.MYADDRESS)
    Observable<AddressListBean> getmyaddress(@HeaderMap Map<String, String> path);
    //添加收货地址
    @POST(ApiService.MYADDRESS1)
    @FormUrlEncoded
    Observable<SyncBean> getAddaddress(@HeaderMap Map<String, String> path, @FieldMap Map<String, String> data);
     //订单数据

    @GET(ApiService.FINDDING)
    Observable<OrderBean> getfindding(@HeaderMap Map<String, Object> path, @QueryMap Map<String,Object> data);

}
