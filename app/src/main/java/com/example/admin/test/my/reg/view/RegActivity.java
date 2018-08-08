package com.example.admin.test.my.reg.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.test.R;
import com.example.admin.test.bean.RegBean;
import com.example.admin.test.my.login.view.LoginActivity;
import com.example.admin.test.server.NewApi;
import com.example.admin.test.server.Retrofit_Net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView reg_close;
    private EditText reg_mobile,reg_password;
    private Button reg_btn;
    private String mMobile;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initViews();
    }

    private void initViews() {
        reg_close = findViewById(R.id.reg_close);
        reg_close = findViewById(R.id.reg_close);
        reg_mobile = findViewById(R.id.reg_mobile);
        reg_password = findViewById(R.id.reg_password);
        reg_btn = findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(this);
        reg_close.setOnClickListener(this);
    }

    public void reg(String path, String mobile, String password){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(path)
                .build();
        Retrofit_Net retrofit_net = retrofit.create(Retrofit_Net.class);
        Call<RegBean> getreg = retrofit_net.getreg(mobile, password);
        getreg.enqueue(new Callback<RegBean>() {
            @Override
            public void onResponse(Call<RegBean> call, Response<RegBean> response) {
                Toast.makeText(RegActivity.this,""+response.body().getMsg(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RegBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_btn:
                mMobile = reg_mobile.getText().toString();
                mPassword = reg_password.getText().toString();
                if (!TextUtils.isEmpty(mMobile) && !TextUtils.isEmpty(mPassword)){
                    reg(NewApi.LOGIN_REG, mMobile, mPassword);
                }else{
                    Toast.makeText(RegActivity.this,"输入框不能为空，晓得不", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.reg_close:
                finish();
                break;
        }
    }
}
