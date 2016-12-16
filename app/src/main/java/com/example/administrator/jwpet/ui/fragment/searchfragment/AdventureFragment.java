package com.example.administrator.jwpet.ui.fragment.searchfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.jwpet.R;

/**
 * @author swd
 * @date 2016/12/15
 * @time 10:57
 * @description
 */

public class AdventureFragment extends Fragment{
    private View mRootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_adventure, container, false);//关联布局文件
        return mRootView;
    }
}
