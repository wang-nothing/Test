package com.example.admin.test.my.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.test.R;
import com.example.admin.test.bean.LoginBean;
import com.example.admin.test.bean.RegBean;
import com.example.admin.test.my.reg.view.RegActivity;
import com.example.admin.test.server.NewApi;
import com.example.admin.test.server.Retrofit_Net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_mobile, login_password;
    private ImageView login_close;
    private Button login_btn;
    private TextView login_login, login_reg;
    private String mMobile;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        login_close = findViewById(R.id.login_close);
        login_mobile = findViewById(R.id.login_mobile);
        login_password = findViewById(R.id.login_password);
        login_close = findViewById(R.id.login_close);
        login_btn = findViewById(R.id.login_btn);
        login_login = findViewById(R.id.login_login);
        login_reg = findViewById(R.id.login_reg);

        login_btn.setOnClickListener(this);
        login_login.setOnClickListener(this);
        login_reg.setOnClickListener(this);
        login_close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                mMobile = login_mobile.getText().toString();
                mPassword = login_password.getText().toString();
                if (!TextUtils.isEmpty(mMobile) && !TextUtils.isEmpty(mPassword)){
                    login(NewApi.LOGIN_REG, mMobile, mPassword);
                }else{
                    Toast.makeText(LoginActivity.this,"输入框不能为空，晓得不", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_login:

                break;
            case R.id.login_reg:
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
                break;
            case R.id.login_close:
                finish();
                break;
        }
    }



    public void login(String path, String mobile, String password){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(path)
                .build();
        Retrofit_Net retrofit_net = retrofit.create(Retrofit_Net.class);
        Call<LoginBean> getlogin = retrofit_net.getlogin(mobile, password);
        getlogin.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                Toast.makeText(LoginActivity.this,""+response.body().getMsg(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
    }
}
