package com.example.work_three.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.work_three.R;
import com.example.work_three.bean.FindShop;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyShopAdapter extends RecyclerView.Adapter<com.example.work_three.adapter.MyShopAdapter.ViewHolder> {
    private Context context;
    FindShop findShop;

    public MyShopAdapter(Context context, FindShop findShop) {
        this.context = context;
        this.findShop=findShop;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_list_one, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    holder.img.setImageURI(Uri.parse(findShop.getResult().get(position).getPic()));
    holder.name.setText(findShop.getResult().get(position).getCommodityName());
    holder.price.setText("¥:"+findShop.getResult().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return findShop.getResult().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            name = itemView.findViewById(R.id.name1);
            price = itemView.findViewById(R.id.price1);

        }
    }
}
