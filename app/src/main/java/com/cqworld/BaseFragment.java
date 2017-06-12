package com.cqworld;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by Administrator on 2016/9/28.
 */
public class BaseFragment extends Fragment {
    private ProgressDialog progress;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    synchronized protected void dialogShow() {
        if(getActivity() == null){
            return;
        }
        try {
            if (null == progress) {
                progress = new ProgressDialog(getActivity());
                progress.setMessage("请稍候……");
                progress.setIndeterminate(true);
                progress.setCancelable(false);
                progress.setCanceledOnTouchOutside(false);
            }
            if (null != progress && !progress.isShowing()) { progress.show(); }
        } catch (Exception e) {
        }
    }

    synchronized protected void dialogDismiss() {
        try {
            if (null != progress && progress.isShowing()) { progress.dismiss(); }
        } catch (Exception e) {
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        dialogDismiss();
    }
}
