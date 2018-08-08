package com.example.admin.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.test.R;
import com.example.admin.test.my.login.view.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by admin on 2018-8-7.
 */

public class Fragment_My extends Fragment implements View.OnClickListener {
    private SimpleDraweeView my_simple;
    private TextView my_login;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        my_simple = mView.findViewById(R.id.my_simple);
        my_login = mView.findViewById(R.id.my_login);
        my_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
