package com.example.weidu_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.weidu_app.R;
import com.example.weidu_app.myadd.ShouHuoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {



    private Context context;
    private List<ShouHuoBean.ResultBean> resultBean;


    public CityAdapter(Context context, List<ShouHuoBean.ResultBean> resultBean) {
        this.context = context;
        this.resultBean = resultBean;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView;
        mView = View.inflate(viewGroup.getContext(), R.layout.item_addrs, null);
        return new CityViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        cityViewHolder.name.setText(resultBean.get(i).getRealName());
        cityViewHolder.phone.setText(resultBean.get(i).getPhone());
        cityViewHolder.addr.setText(resultBean.get(i).getAddress());

    }

    @Override
    public int getItemCount() {
        return resultBean.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.addr)
        TextView addr;
        @BindView(R.id.check)
        RadioButton check;
        @BindView(R.id.change)
        Button change;
        @BindView(R.id.del)
        Button del;
        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
