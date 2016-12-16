package com.example.administrator.jwpet.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.jwpet.R;
import com.example.administrator.jwpet.ui.widget.TestArrayAdapter;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvSetting;

    private Spinner mSArea;
    private Spinner mSServer;
    private TextView mTvTitle;

    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<String> mAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        mTvTitle = (TextView)findViewById(R.id.tv_title);
        mTvTitle.setText(getString(R.string.setting));
        mIvSetting = (ImageView)findViewById(R.id.iv_setting);
        mIvSetting.setVisibility(View.INVISIBLE);

        mSArea = (Spinner) findViewById(R.id.s_area);
        mAdapter = new TestArrayAdapter(this,getResources().getStringArray(R.array.area));
        mSArea.setAdapter(mAdapter);
        mSArea.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
        mSArea.setVisibility(View.VISIBLE);
        mSArea.setSelection(4);

        mSServer = (Spinner)findViewById(R.id.s_server);
        mAdapter2 =  new TestArrayAdapter(this,getResources().getStringArray(R.array.d5));
        mSServer.setAdapter(mAdapter2);
        mSServer.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
        mSServer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {

    }

    //使用XML形式操作
    class SpinnerXMLSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
//            Toast.makeText(getActivity(),adapter.getItem(arg2)+"",Toast.LENGTH_SHORT).show();
            mSArea.setSelection(4);
            mSServer.setSelection(0);
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
}
