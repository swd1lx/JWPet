package com.example.administrator.jwpet.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.jwpet.R;
import com.example.administrator.jwpet.ui.fragment.DailyFragment;
import com.example.administrator.jwpet.ui.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout mRlSearch;
    private RelativeLayout mRlSetting;
    private ArrayList<Fragment> mFragmentList;
    private int currentIndex = 0;
    private Fragment mCurrentFrgment;
    private List<TextView> mListTextView;

    private ImageView mIvSearch;
    private ImageView mIvDaily;
    private TextView mTvSearch;
    private TextView mTvSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mRlSearch = (RelativeLayout)findViewById(R.id.rl_search);
        mRlSearch.setOnClickListener(this);
        mRlSetting = (RelativeLayout)findViewById(R.id.rl_daily);
        mRlSetting.setOnClickListener(this);

        mIvSearch = (ImageView)findViewById(R.id.iv_search);
        mIvDaily = (ImageView)findViewById(R.id.iv_daily);
        mIvDaily.setOnClickListener(this);

        mTvSearch = (TextView)findViewById(R.id.tv_search);
        mTvSetting = (TextView)findViewById(R.id.tv_daily);
        mListTextView = new ArrayList<TextView>();
        mListTextView.add(mTvSearch);
        mListTextView.add(mTvSetting);

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new SearchFragment());
        mFragmentList.add(new DailyFragment());
        changeTab(0);
    }

    private void changeTab(int index) {
        currentIndex = index;
        mRlSearch.setSelected(index == 0);
        mRlSetting.setSelected(index == 1);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            ft.hide(mCurrentFrgment);
        }
        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(mFragmentList.get(currentIndex).getClass().getName());

        if (null == fragment) {
            //如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = mFragmentList.get(index);
        }
        mCurrentFrgment = fragment;

        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!fragment.isAdded()) {
            ft.add(R.id.fragment, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        if(view==mRlSearch){
            changeTab(0);
            mIvSearch.setBackgroundResource(R.drawable.search_selected);
            mIvDaily.setBackgroundResource(R.drawable.calender);
            setView(0);
            return;
        }


        if (view ==mRlSetting){
            changeTab(1);
            mIvSearch.setBackgroundResource(R.drawable.search);
            mIvDaily.setBackgroundResource(R.drawable.calender_selected);
            setView(1);
        }
    }

    private void setView(int position) {
        for (int i = 0; i < 2; i++) {
            if (i==position) {
                mListTextView.get(i).setTextColor(getResources().getColor(R.color.general_blue));
            }else {
                mListTextView.get(i).setTextColor(getResources().getColor(R.color.text_gray));
            }
        }
    }
}
