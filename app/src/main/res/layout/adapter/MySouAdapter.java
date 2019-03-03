package com.example.work_three.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.work_three.R;
import com.example.work_three.bean.SouBean;

import java.util.List;

public class MySouAdapter extends RecyclerView.Adapter{
    private Context context;
    private SouBean mlist;
    private MyShangViewHolder myShangViewHolder;
    public MySouAdapter(Context context, SouBean souBean) {
        this.context=context;
        this.mlist=souBean;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.sou_layout, null);
        myShangViewHolder = new MyShangViewHolder(view);
        return myShangViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        myShangViewHolder.name.setText(mlist.getResult().get(i).getCommodityName());
        myShangViewHolder.price.setText(mlist.getResult().get(i).getPrice()+"");
        Glide.with(context).load((mlist.getResult().get(i).getMasterPic())).into(myShangViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return mlist.getResult().size();
    }

    private class MyShangViewHolder extends RecyclerView.ViewHolder {
        private Button gouwu;
        CheckBox shop_check;
        ImageView img;
        TextView name;
        TextView price;
        public MyShangViewHolder(View view) {
            super(view);
            img = itemView.findViewById(R.id.img_sou);
            price = itemView.findViewById(R.id.price_sou);
            name =  itemView.findViewById(R.id.name_sou);
        }
    }
    public void setList(List<SouBean.ResultBean> list) {
        this.mlist = (SouBean) list;
        notifyDataSetChanged();
    }
}
