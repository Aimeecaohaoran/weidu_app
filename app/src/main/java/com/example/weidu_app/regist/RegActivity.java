package com.example.weidu_app.regist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidu_app.R;
import com.example.weidu_app.ShowActivity;
import com.example.weidu_app.bean.RegBean;
import com.example.weidu_app.login.LoginActivity;
import com.example.weidu_app.regist.presenter.RegistPresenter;
import com.example.weidu_app.regist.view.IRegistView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegActivity extends AppCompatActivity  implements IRegistView, View.OnClickListener {
    @BindView(R.id.et_reg_name)
    EditText etRegName;
    @BindView(R.id.et_reg_yan)
    EditText etRegYan;
    @BindView(R.id.et_reg_pwd)
    EditText etRegPwd;
    @BindView(R.id.text_login_yi)
    Button textLogin;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    private Unbinder bind;
    private RegistPresenter registPresenter;
    private String password;
    private String username;
    private AlertDialog alertDialog;
    private Button yesremeber;
    private Button noremeber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    private void initView() {
        bind = ButterKnife.bind(RegActivity.this);
        registPresenter = new RegistPresenter(this);
        btnRegist.setOnClickListener(this);
        textLogin.setOnClickListener(this);
    }

    @Override
    public void showMsg(Object obj) {
        RegBean regBean= (RegBean) obj;
        //Toast.makeText(RegisterActivity.this,""+msg,Toast.LENGTH_SHORT).show();
        //注册成功后弹出对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(RegActivity.this);
        View view = View.inflate(RegActivity.this, R.layout.regis_is_remeber_alert, null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        yesremeber = view.findViewById(R.id.yes_remeber);
        noremeber = view.findViewById(R.id.no_remeber);
        //记住的话给登录页面返回值
        yesremeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//关闭弹框
                alertDialog.dismiss();
                Intent intent = new Intent();
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("regisischeck",true);
                setResult(2,intent);
                finish(); //结束当前的activity
            }
        });
        //不记住的话直接关闭弹框
        noremeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }});

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_regist:
                //注册
                submit();
                break;
            case R.id.text_login_yi:
                //取消注册返回登录界面
                startActivity(new Intent(RegActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }
    private void submit() {
        //非空验证
        username = etRegName.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        password = etRegPwd.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //去请求数据注册
        registPresenter.registPre(username,password);
    }

    //解决mvp内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        registPresenter.destory();
    }
}
