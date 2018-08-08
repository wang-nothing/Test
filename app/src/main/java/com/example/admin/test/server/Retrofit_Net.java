package com.example.admin.test.server;

import com.example.admin.test.bean.LoginBean;
import com.example.admin.test.bean.RegBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by admin on 2018-8-7.
 */

public interface Retrofit_Net {
    //注册
    @POST("user/reg")
    Call<RegBean> getreg(@Query("mobile") String mobile, @Query("password") String password);

    @POST("user/login")
    Call<LoginBean> getlogin(@Query("mobile") String mobile, @Query("password") String password);

}
