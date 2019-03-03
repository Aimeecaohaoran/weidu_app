package com.example.weidu_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.weidu_app.R;
import com.example.weidu_app.bean.ErJi;
import com.example.weidu_app.bean.Message;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ErJiAdapter extends RecyclerView.Adapter<ErJiAdapter.TopRecyclerView> {

    private List<ErJi.ResultBean> mList;
    private Context mContext;

    public ErJiAdapter(Context mContext,List<ErJi.ResultBean> mList) {
        this.mContext = mContext;
        this.mList=mList;
        mList=new ArrayList<>();
    }

    public void setData(List<ErJi.ResultBean> datas) {
        mList.clear();
        if (datas!=null){
            mList.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopRecyclerView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_popup,viewGroup,false);
        return new TopRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRecyclerView viewHolder, final int i) {
        TopRecyclerView topRecyclerView= viewHolder;

        Log.i("TAG", "onBindViewHolder: "+mList.get(i).getName());
        topRecyclerView.recycle_top_item.setText(mList.get(i).getName());
        topRecyclerView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Message(mList.get(i).getId()));
                System.out.println("商品2"+mList.get(i).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class TopRecyclerView extends RecyclerView.ViewHolder{
        public final TextView recycle_top_item;
        public TopRecyclerView(@NonNull View itemView) {
            super(itemView);
            recycle_top_item=itemView.findViewById(R.id.recycle_top_item);
        }
    }
}