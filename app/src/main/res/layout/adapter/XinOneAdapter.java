package com.example.work_three.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.work_three.R;
import com.example.work_three.XiangActivity;
import com.example.work_three.bean.EventBusBean;
import com.example.work_three.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class XinOneAdapter extends RecyclerView.Adapter<com.example.work_three.adapter.XinOneAdapter.HlistViewHodel> {
    Context context;
    List<HomeBean.ResultBean.RxxpBean.CommodityListBean> list;
    public XinOneAdapter(Context context, List<HomeBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context=context;
        this.list=list;

    }
    @NonNull
    @Override
    public HlistViewHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView;
        mView=View.inflate(viewGroup.getContext(),R.layout.layout_list_one,null);
        return new HlistViewHodel(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HlistViewHodel hlistViewHodel, final int i) {
        hlistViewHodel.name.setText(list.get(i).getCommodityName());
        hlistViewHodel.price.setText(list.get(i).getPrice());
        hlistViewHodel.img.setImageURI(list.get(i).getMasterPic());
        hlistViewHodel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //eventbus传递黏性事件给Xiangmainactivity
                //传一个eventbusbean对象过去
                EventBusBean eventBusBean = new EventBusBean(list.get(i).getCommodityId());
                EventBus.getDefault().postSticky(eventBusBean);
                //跳转
                context.startActivity(new Intent(context, XiangActivity.class));
                EventBus.getDefault().post(list.get(i).getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HlistViewHodel extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView name;
        TextView price;
        public HlistViewHodel(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            price = itemView.findViewById(R.id.price1);
            name =  itemView.findViewById(R.id.name1);
        }
    }
}
