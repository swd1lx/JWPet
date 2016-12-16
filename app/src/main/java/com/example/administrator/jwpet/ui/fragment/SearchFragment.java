package com.example.administrator.jwpet.ui.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jwpet.R;
import com.example.administrator.jwpet.ui.activity.AddCdActivity;
import com.example.administrator.jwpet.ui.activity.SettingActivity;
import com.example.administrator.jwpet.ui.fragment.searchfragment.AdventureFragment;
import com.example.administrator.jwpet.ui.fragment.searchfragment.PetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swd
 * @date 2016/12/15
 * @time 10:32
 * @description
 */

public class SearchFragment extends Fragment implements View.OnClickListener{
    private View mRootView;
    private ImageView mImgCursor;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private TextView mTvPet;
    private TextView mTvAdventure;
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private ImageView mIvSetting;
    private ImageView mIvAdd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search, container, false);//关联布局文件
        initView();

        addListener();
        return mRootView;
    }


    @Override
    public void onClick(View view) {
        if (view == mTvPet) {
            mViewPager.setCurrentItem(0);
        }else if (view==mTvAdventure){
            mViewPager.setCurrentItem(1);
        }else if (view == mIvSetting){
            getActivity().startActivity(new Intent(getActivity(), SettingActivity.class));
        }else if (view ==mIvAdd){
            getActivity().startActivity(new Intent(getActivity(), AddCdActivity.class));
        }
    }

    private void initView() {
        mIvAdd = (ImageView)mRootView.findViewById(R.id.iv_add);
        mIvAdd.setOnClickListener(this);
        mIvSetting = (ImageView)mRootView.findViewById(R.id.iv_setting);
        mIvSetting.setOnClickListener(this);
        mImgCursor = (ImageView) mRootView.findViewById(R.id.img_cursor);
        mTvPet = (TextView)mRootView.findViewById(R.id.tv_title_pet);
        mTvPet.setOnClickListener(this);
        mTvAdventure = (TextView)mRootView.findViewById(R.id.tv_title_adventure);
        mTvAdventure.setOnClickListener(this);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.vp_wait_to_check);

        initOffSetData(2);

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new PetFragment());
        mFragmentList.add(new AdventureFragment());

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragmentList.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    private void addListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Animation animation = new TranslateAnimation(currIndex*one,position*one,0,0);//平移动画
                currIndex = position;
                animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
                animation.setDuration(300);//动画持续时间0.2秒
                mImgCursor.startAnimation(animation);//是用ImageView来显示动画的
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initOffSetData(int i) {
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.drawable.view_pager_bg).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW /i - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        mImgCursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
    }
}
