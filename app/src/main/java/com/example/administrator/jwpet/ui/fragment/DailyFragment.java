package com.example.administrator.jwpet.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jwpet.R;
import com.example.administrator.jwpet.ui.activity.SettingActivity;

/**
 * @author swd
 * @date 2016/12/15
 * @time 11:00
 * @description
 */

public class DailyFragment extends Fragment implements View.OnClickListener{
    private View mRootView;
    private TextView mTvTitle;
    private ImageView mIvSetting;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_daily, container, false);//关联布局文件

        initView();


        return mRootView;
    }


    private void initView() {
        mTvTitle = (TextView)mRootView.findViewById(R.id.tv_title);
        mTvTitle.setText(getActivity().getString(R.string.daily));
        mIvSetting = (ImageView)mRootView.findViewById(R.id.iv_setting);
        mIvSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mIvSetting){
            getActivity().startActivity(new Intent(getActivity(), SettingActivity.class));
        }
    }
}
