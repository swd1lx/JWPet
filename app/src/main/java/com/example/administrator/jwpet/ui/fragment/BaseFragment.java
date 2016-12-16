package com.example.administrator.jwpet.ui.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

/**
 * @author swd
 * @date 2016/11/7
 * @time 17:52
 * @description
 */

public class BaseFragment extends Fragment {
    public static final int STATE_INIT = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOAD_MORE = 2;
    private int mModelState = STATE_INIT;
    private ProgressDialog mLoadingDialog;

    protected void showLoadingDialog(String message) {
        showLoadingDialog(message, null, true);
    }

    protected void showLoadingDialog(String message, boolean cancelable) {
        showLoadingDialog(message, null, cancelable);
    }

    protected void showLoadingDialog(int resId) {
        showLoadingDialog(getString(resId), null, true);
    }

    protected void showLoadingDialog(String message, DialogInterface.OnCancelListener listener) {
        showLoadingDialog(message, listener, true);
    }

    protected void showLoadingDialog(String message, DialogInterface.OnCancelListener listener, boolean cancelable) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            return;
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(getActivity());
            mLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mLoadingDialog.setMessage(message);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setOnCancelListener(listener);
        } else {
            mLoadingDialog.setMessage(message);
        }
        mLoadingDialog.show();
    }

    protected void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
            mLoadingDialog = null;
        }
    }

    public int getModelState() {
        return mModelState;
    }

    public void setModelState(int modelState) {
        this.mModelState = modelState;
    }
}
