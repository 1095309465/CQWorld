package com.cqworld.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.cqworld.view.LoadDataView;

/**
 * Created by 瞬ooOo on 2017/6/12.
 */

public class CQUtils {
    public static LoadDataView initLoadDataView(Context context, View view, View.OnClickListener errorListner) {
        if (null == context || null == view) {
            return null;
        }

        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup group = (ViewGroup) view;
        ViewGroup parent = (ViewGroup) group.getParent();
        LoadDataView loadView = null;

        if (null != parent) {
            parent.removeView(group);
            loadView = new LoadDataView(context, group);
            parent.addView(loadView);
        }

        if (loadView != null) {
            loadView.setErrorListner(errorListner);
        }

        return loadView;

    }
    public static Fragment switchFragment(FragmentActivity activity, int containerViewId, Fragment currentFragment, Fragment toFragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (null == currentFragment) {
            fragmentTransaction.add(containerViewId, toFragment);
        } else {
            fragmentTransaction.hide(currentFragment);
            if (toFragment.isAdded()) {
                fragmentTransaction.show(toFragment);
            } else {
                fragmentTransaction.add(containerViewId, toFragment);
            }
        }
        fragmentTransaction.commit();
        return toFragment;
    }
}
