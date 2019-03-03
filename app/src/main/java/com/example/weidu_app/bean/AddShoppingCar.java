package com.example.weidu_app.bean;

public class AddShoppingCar {
    private int commodityId;
    private int count;

    public AddShoppingCar(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    @Override
    public String toString() {
        return "AddShoppingCar{" +
                "commodityId=" + commodityId +
                ", count=" + count +
                '}';
    }
}
