package com.example.weidu_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weidu_app.R;
import com.example.weidu_app.bean.EventBusBean;
import com.example.weidu_app.bean.HomeBean;

import com.example.weidu_app.xiang.XiangActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class XinSanAdapter extends RecyclerView.Adapter <XinSanAdapter.SanListViewHolder> {
    private Context context;
    List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList2;

    public XinSanAdapter(Context context, List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList2) {
        this.context = context;
        this.commodityList2 = commodityList2;

    }

    @NonNull
    @Override
    public SanListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.layout_list_san, null);
        SanListViewHolder sanListViewHolder = new SanListViewHolder(view);
        return sanListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SanListViewHolder sanListViewHolder, final int i) {
        sanListViewHolder.name.setText(commodityList2.get(i).getCommodityName());
        sanListViewHolder.price.setText(commodityList2.get(i).getPrice());
        sanListViewHolder.img.setImageURI(commodityList2.get(i).getMasterPic());
        sanListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //eventbus传递黏性事件给Xiangmainactivity
                //传一个eventbusbean对象过去
                EventBusBean eventBusBean = new EventBusBean(commodityList2.get(i).getCommodityId());
                EventBus.getDefault().postSticky(eventBusBean);
                //跳转
                context.startActivity(new Intent(context, XiangActivity.class));
                EventBus.getDefault().post(commodityList2.get(i).getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodityList2.size();
    }

    public class SanListViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView name;
        TextView price;

        public SanListViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image3);
            price = itemView.findViewById(R.id.price3);
            name = itemView.findViewById(R.id.title3);
        }
    }
}
