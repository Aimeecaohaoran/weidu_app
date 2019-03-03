package com.example.work_three.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.work_three.R;
import com.example.work_three.bean.HomeBean;
import com.example.work_three.bean.NewsBeanTwo;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MyXinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    HomeBean homeBean;
    NewsBeanTwo newsBeanTwo;
    private final int BANNER_VIEW = 0;
    private final int RXXP_VIEW = 1;
    private final int PZSH_VIEW = 2;
    private final int MLSS_VIEW = 3;
    private XinOneAdapter xinOneAdapter;
    private XinTwoAdapter xinTwoAdapter;
    private XinSanAdapter xinSanAdapter;
    private List<HomeBean.ResultBean.RxxpBean.CommodityListBean> commodityList1;
    private List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList3;
    private List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2;
    public MyXinAdapter(Context context, HomeBean homeBean, NewsBeanTwo newsBeanTwo) {
        this.context =context;
        this.homeBean=homeBean;
        this.newsBeanTwo=newsBeanTwo;
    }


    @Override
    public int getItemViewType(int position) {
        switch (position)
        {
            case 0:
                return BANNER_VIEW;
            case 1:
                return RXXP_VIEW;
            case 2:
                return PZSH_VIEW;

        }
        return MLSS_VIEW;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView;
        if(i==BANNER_VIEW)
        {
            mView=View.inflate(viewGroup.getContext(),R.layout.shouye_0,null);

            return new com.example.work_three.adapter.MyXinAdapter.Item0ViewHolder(mView);
        }else if(i==RXXP_VIEW)
        {
            mView=View.inflate(viewGroup.getContext(),R.layout.shouye_1,null);
            return new com.example.work_three.adapter.MyXinAdapter.Item1ViewHolder(mView);
        }else if(i == PZSH_VIEW)
        {
            mView=View.inflate(viewGroup.getContext(),R.layout.shouye_2,null);
            return new com.example.work_three.adapter.MyXinAdapter.Item2ViewHolder(mView);
        }
        else {
            mView=View.inflate(viewGroup.getContext(),R.layout.shouye_3,null);
            return new com.example.work_three.adapter.MyXinAdapter.Item3ViewHolder(mView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
       if (viewHolder instanceof com.example.work_three.adapter.MyXinAdapter.Item0ViewHolder){
            final List<String> list = new ArrayList<>();
            for (int j = 0; j<newsBeanTwo.getResult().size(); j++){
                list.add(newsBeanTwo.getResult().get(j).getImageUrl());
            }
            ((com.example.work_three.adapter.MyXinAdapter.Item0ViewHolder) viewHolder).xbanner.setData(list,null);
            ((com.example.work_three.adapter.MyXinAdapter.Item0ViewHolder) viewHolder).xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(list.get(i)).into((ImageView) view);
                }
            });
            ((Item0ViewHolder) viewHolder).xbanner.setPageTransformer(Transformer.Default);
           ((Item0ViewHolder) viewHolder).xbanner.startAutoPlay();
           ((Item0ViewHolder) viewHolder).xbanner.setPageChangeDuration(1000);
        }
       if(viewHolder instanceof com.example.work_three.adapter.MyXinAdapter.Item1ViewHolder)
        {
            ((com.example.work_three.adapter.MyXinAdapter.Item1ViewHolder) viewHolder).rxxp.setText(homeBean.getResult().getRxxp().getName());
            System.out.println("第一个"+homeBean.getResult().getRxxp().getName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((Item1ViewHolder) viewHolder).rv.setLayoutManager(linearLayoutManager);
            commodityList1 = homeBean.getResult().getRxxp().getCommodityList();
            xinOneAdapter = new XinOneAdapter(context,commodityList1);
           ((com.example.work_three.adapter.MyXinAdapter.Item1ViewHolder) viewHolder).rv.setAdapter(xinOneAdapter);


        }
        if(viewHolder instanceof com.example.work_three.adapter.MyXinAdapter.Item2ViewHolder){
            ((com.example.work_three.adapter.MyXinAdapter.Item2ViewHolder) viewHolder).pzsh.setText(homeBean.getResult().getPzsh().getName());
             System.out.println("第二个"+homeBean.getResult().getPzsh().getName());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
            gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            ((Item2ViewHolder) viewHolder).rv2.setLayoutManager(gridLayoutManager);
            commodityList2 = homeBean.getResult().getPzsh().getCommodityList();
            xinTwoAdapter = new XinTwoAdapter(context,commodityList2);
            ((com.example.work_three.adapter.MyXinAdapter.Item2ViewHolder) viewHolder).rv2.setAdapter(xinTwoAdapter);

        }

        if(viewHolder instanceof com.example.work_three.adapter.MyXinAdapter.Item3ViewHolder)
        {
            ((Item3ViewHolder) viewHolder).mlss.setText(homeBean.getResult().getMlss().getName());
            System.out.println("第三个"+homeBean.getResult().getMlss().getName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((Item3ViewHolder) viewHolder).rv3.setLayoutManager(linearLayoutManager);
            commodityList3 = homeBean.getResult().getMlss().getCommodityList();
            xinSanAdapter = new XinSanAdapter(context,commodityList3);
            ((com.example.work_three.adapter.MyXinAdapter.Item3ViewHolder) viewHolder).rv3.setAdapter(xinSanAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
    class Item0ViewHolder extends RecyclerView.ViewHolder {
        XBanner xbanner;
        public Item0ViewHolder(@NonNull View itemView) {
            super(itemView);
             xbanner = itemView.findViewById(R.id.xbanner);
        }
    }
    class Item1ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        TextView rxxp;

        public Item1ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            rxxp = itemView.findViewById(R.id.rxxptv);
        }
    }

    class Item2ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv2;
        TextView pzsh;
        public Item2ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv2 = itemView.findViewById(R.id.rv2);
            pzsh = itemView.findViewById(R.id.pzshtv);
        }
    }
    class Item3ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rv3;
        TextView mlss;
        public Item3ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv3 = itemView.findViewById(R.id.rv3);
            mlss = itemView.findViewById(R.id.mlsstv);
        }
    }

}
