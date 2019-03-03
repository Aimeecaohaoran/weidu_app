package com.example.weidu_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weidu_app.R;
import com.example.weidu_app.bean.QuanBean;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {
    private Context context;
    private List<QuanBean.ResultBean> list;

    public CircleAdapter(Context context, List<QuanBean.ResultBean> list) {
        this.context = context;
        this.list=list;
    }

    public void setList(List<QuanBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<QuanBean.ResultBean> list) {
        this.list .addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_circle, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getHeadPic()).into(viewHolder.circleSimpleHead);
        viewHolder.circleSimpleTitle.setText(list.get(i).getNickName());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                new java.util.Date(list.get(i).getCreateTime()));
        viewHolder.circleSimpleTime.setText(date);
        viewHolder.circleSimpleZhu.setText(list.get(i).getContent());
        viewHolder.circleTextGive.setText(list.get(i).getGreatNum()+"");
        String image = list.get(i).getImage();
        String[] split = image.split("\\,");
        final List<String> lists = Arrays.asList(split);
        if (image==""){
            viewHolder.linear.setVisibility(View.GONE);
        }else {
            Glide.with(context).load(lists.get(0)).into(viewHolder.circleSimplePic);
        }
        if (list.get(i).getWhetherGreat()==1){
            viewHolder.circleSimpleGive.setBackgroundResource(R.mipmap.common_btn_prise_s_xhdpi);
        }else {
            viewHolder.circleSimpleGive.setBackgroundResource(R.mipmap.common_btn_prise_n_xhdpi);
        }

        viewHolder.circleSimpleGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (greatClick!=null){
                    if (greatClick!=null){
                        if (list.get(i).getWhetherGreat()==1){
                            list.get(i).setWhetherGreat(2);
                            list.get(i).setGreatNum(list.get(i).getGreatNum()-1);
                        }else {
                            list.get(i).setWhetherGreat(1);
                            list.get(i).setGreatNum(list.get(i).getGreatNum()+1);
                        }
                        greatClick.click(list.get(i).getId(),list.get(i).getWhetherGreat()==1);
                        notifyItemChanged(i);
                    }

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView circleSimpleHead;
        TextView circleSimpleTitle;
        TextView circleSimpleTime;
        TextView circleSimpleZhu;
        ImageView circleSimplePic;
        ImageView circleSimpleGive;
        TextView circleTextGive;
        LinearLayout linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linear=itemView.findViewById(R.id.linear);
            circleSimpleHead=itemView.findViewById(R.id.circle_simple_head);
            circleSimpleTitle=itemView.findViewById(R.id.circle_simple_title);
            circleSimpleTime=itemView.findViewById(R.id.circle_simple_time);
            circleSimpleZhu=itemView.findViewById(R.id.circle_simple_zhu);
            circleSimplePic=itemView.findViewById(R.id.circle_simple_pic);
            circleSimpleGive=itemView.findViewById(R.id.circle_simple_give);
            circleTextGive=itemView.findViewById(R.id.circle_text_give);
        }
    }
    private GreatClick greatClick;
    public void setGreatClick(GreatClick greatClick) {
        this.greatClick = greatClick;
    }
    public interface GreatClick{
        void click(int circleId, boolean isGreat);
    }
}