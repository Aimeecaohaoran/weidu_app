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

public class XinTwoAdapter extends RecyclerView.Adapter <com.example.work_three.adapter.XinTwoAdapter.TwoListViewHolder>{
private Context context;
    List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2;


    public XinTwoAdapter(Context context, List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2) {
        this.context = context;
        this.commodityList2=commodityList2;
    }


    @NonNull
@Override
public TwoListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.layout_list_two, null);
        TwoListViewHolder TwoListViewHolder = new TwoListViewHolder(view);
        return TwoListViewHolder;
        }

@Override
public void onBindViewHolder(@NonNull final TwoListViewHolder TwoListViewHolder, final int i) {
     TwoListViewHolder.name.setText(commodityList2.get(i).getCommodityName());
     TwoListViewHolder.price.setText(commodityList2.get(i).getPrice());
    TwoListViewHolder.img.setImageURI(commodityList2.get(i).getMasterPic());
    TwoListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //通过EventBus发消息
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

public class TwoListViewHolder extends RecyclerView.ViewHolder {
    SimpleDraweeView img;
    TextView name;
    TextView price;
    public TwoListViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.image2);
        price = itemView.findViewById(R.id.price2);
        name =  itemView.findViewById(R.id.title2);
    }
}
}
