package com.example.weidu_app.dingdan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weidu_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingSubmitActivity extends AppCompatActivity {

    @BindView(R.id.shopping_submit_address)
    TextView shoppingSubmitAddress;
    @BindView(R.id.shopping_submit_show_lin_name)
    TextView shoppingSubmitShowLinName;
    @BindView(R.id.shopping_submit_show_lin_phone)
    TextView shoppingSubmitShowLinPhone;
    @BindView(R.id.shopping_submit_show_lin_address)
    TextView shoppingSubmitShowLinAddress;
    @BindView(R.id.shopping_submit_show_down_up)
    ImageView shoppingSubmitShowDownUp;
    @BindView(R.id.shopping_submit_ryl)
    RecyclerView shoppingSubmitRyl;
    @BindView(R.id.shopping_submit_show_address)
    RecyclerView shoppingSubmitShowAddress;
    @BindView(R.id.shopping_submit_sumsize)
    TextView shoppingSubmitSumsize;
    @BindView(R.id.shopping_submit_sumprice)
    TextView shoppingSubmitSumprice;
    @BindView(R.id.shopping_submit_submit)
    TextView shoppingSubmitSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_submit);
        ButterKnife.bind(this);
    }
}
