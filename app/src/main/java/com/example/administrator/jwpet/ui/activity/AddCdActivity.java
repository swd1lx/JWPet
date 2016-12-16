package com.example.administrator.jwpet.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jwpet.R;

public class AddCdActivity extends AppCompatActivity {
    private ImageView mIvSetting;
    private TextView mTvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cd);

        initView();
    }

    private void initView() {
        mTvTitle = (TextView)findViewById(R.id.tv_title);
        mTvTitle.setText(getString(R.string.add));
        mIvSetting = (ImageView)findViewById(R.id.iv_setting);
        mIvSetting.setVisibility(View.INVISIBLE);
    }
}
