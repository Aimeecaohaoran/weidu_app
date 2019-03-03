package com.example.weidu_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weidu_app.R;
import com.example.weidu_app.bean.ByIdBean;
import com.example.weidu_app.bean.EventBusBean;
import com.example.weidu_app.xiang.XiangActivity;


import org.greenrobot.eventbus.EventBus;

public class ByIdAdapter extends RecyclerView.Adapter<ByIdAdapter.ViewHolder> {

    ByIdBean byIdBean;
    private Context context;

    public ByIdAdapter(Context context, ByIdBean byIdBean) {
        this.context = context;
        this.byIdBean = byIdBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pin, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(context).load(byIdBean.getResult().get(i).getMasterPic()).into(viewHolder.imageView);
        viewHolder.name.setText(byIdBean.getResult().get(i).getCommodityName());
        viewHolder.price.setText(byIdBean.getResult().get(i).getPrice());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //eventbus传递黏性事件给Xiangmainactivity
                //传一个eventbusbean对象过去
                EventBusBean eventBusBean = new EventBusBean(byIdBean.getResult().get(i).getCommodityId());
                EventBus.getDefault().postSticky(eventBusBean);
                //跳转
               context.startActivity(new Intent(context, XiangActivity.class));
                EventBus.getDefault().post(byIdBean.getResult().get(i).getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return byIdBean.getResult().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}