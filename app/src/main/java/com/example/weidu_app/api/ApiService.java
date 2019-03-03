package com.example.weidu_app.api;

public class ApiService {
    public static final String BASE_URL = "http://172.17.8.100/";
    public static final String LOGIN="small/user/v1/login";
    public static final String REG_URL = "small/user/v1/register";
    public static final String SHOP_LIE = "small/commodity/v1/commodityList";
    public static final String SHOP_CIRCLE = "small/circle/v1/findCircleList";
    public static final String BANNER = "small/commodity/v1/bannerShow";
    public static final String XIANG = "small/commodity/v1/findCommodityDetailsById";
    public static final String SOUSUO = " small/commodity/v1/findCommodityByKeyword";
    public static final String YIJI = " small/commodity/v1/findFirstCategory";
    public static final String ERJI = " small/commodity/v1/findSecondCategory";
    public static final String ERSHOP = "small/commodity/v1/findCommodityByCategory";
    public static final String DIANZAN = "small/circle/verify/v1/addCircleGreat";
    public static final String QUXIAODIANZAN = "small/circle/verify/v1/cancelCircleGreat";
    public static final String JIARUSHOP = "small/order/verify/v1/syncShoppingCart";
    public static final String CHASHOP = "small/order/verify/v1/findShoppingCart";
    //收货地址列表
    public static final String MYADDRESS = "small/user/verify/v1/receiveAddressList";
    //新增收货地址
    public static final String MYADDRESS1 = "small/user/verify/v1/addReceiveAddress";
    public static final String FINDDING = "small/order/verify/v1/findOrderListByStatus";
    //删除订单
    public static final String SHANCHUDINGDAN = "small/order/verify/v1/deleteOrder";
    //收货
    public static final String SHOUHUO = "small/order/verify/v1/confirmReceipt";
    //查询订单详细数据
    public static final String DINGDANSHUJV = "small/order/verify/v1/findOrderInfo";



}
