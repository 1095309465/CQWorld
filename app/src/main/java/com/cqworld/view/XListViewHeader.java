/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.cqworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.cqworld.R;
import com.cqworld.util.DisplayUtil;
import com.cqworld.util.ResourceUtils;

import java.util.ArrayList;

public class XListViewHeader extends LinearLayout {

    private LinearLayout mContainer;
    private ImageView mArrowTextView;
    private ImageView mImageView;
    private TextView mHintTextView;
    private TextView mHintTextView2;
    private int mState = STATE_NORMAL;

    //    private Animation mRotateUpAnim;
    //    private Animation mRotateDownAnim;
    private Animation mImageViewAnimation;

    private final int ROTATE_ANIM_DURATION = 180;

    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    private ArrayList<Integer> pullRefreshDrawable;


    public XListViewHeader(Context context) {
        super(context);
        initView(context);
        initData(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initData(context);
    }

    private void initView(Context context) {
        // 初始情况，设置下拉刷新view高度为0
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        mContainer = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);

        mArrowTextView = (ImageView)findViewById(R.id.xlistview_header_arrow);
        mHintTextView = (TextView)findViewById(R.id.xlistview_header_hint_textview);
        mHintTextView2 = (TextView)findViewById(R.id.xlistview_header_hint_textview2);
        mImageView = (ImageView)findViewById(R.id.xlistview_header_progressbar);

        mImageViewAnimation = AnimationUtils.loadAnimation(context, R.anim.rote);
        mImageViewAnimation.setInterpolator(new LinearInterpolator());
        mImageViewAnimation.setDuration(560);
        mImageViewAnimation.setRepeatCount(Animation.INFINITE);
        mImageViewAnimation.setRepeatMode(Animation.RESTART);


    }

    private void initData(Context context) {
        pullRefreshDrawable = new ArrayList<>();
        for (int i = 1; i <= 46; i++) {
            int drawableId;
            if (i < 10) {
                drawableId = ResourceUtils.getMipmapId(context, "pull_refresh_anim_0" + i);
            } else {
                drawableId = ResourceUtils.getMipmapId(context, "pull_refresh_anim_" + i);
            }
            pullRefreshDrawable.add(drawableId);
        }
    }

    public void stop() {
        mImageView.clearAnimation();
    }

    public void start() {
        stop();
        mImageView.startAnimation(mImageViewAnimation);
    }

    public void setState(int state) {
        if (state == mState) {
            return;
        }

        if (state == STATE_REFRESHING) { // 显示进度
            start();
            mArrowTextView.setVisibility(View.INVISIBLE);
            mImageView.setVisibility(View.VISIBLE);

            mHintTextView.setVisibility(INVISIBLE);
            mHintTextView2.setVisibility(VISIBLE);

        } else { // 显示箭头图片
            stop();
            mArrowTextView.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.INVISIBLE);

            mHintTextView.setVisibility(VISIBLE);
            mHintTextView2.setVisibility(INVISIBLE);
        }

        switch (state) {
            case STATE_NORMAL:
                mHintTextView.setText(R.string.xlistview_header_hint_normal);
                mHintTextView2.setText(R.string.xlistview_header_hint_normal);
                //                stop();
                break;
            case STATE_READY:
                if (mState != STATE_READY) {
                    mArrowTextView.setVisibility(View.INVISIBLE);
                    mImageView.setVisibility(View.VISIBLE);

                    mHintTextView.setVisibility(INVISIBLE);
                    mHintTextView2.setVisibility(VISIBLE);

                    mHintTextView2.setText(R.string.xlistview_header_hint_ready);
                    //                    stop();
                }
                break;
            case STATE_REFRESHING:
                mHintTextView2.setText(R.string.xlistview_header_hint_loading);
                break;
            default:
                break;
        }

        mState = state;
    }

    public void setVisiableHeight(int height, int heightTotle) {
        if (height < 0) {
            height = 0;
        }
        LayoutParams lp = (LayoutParams)mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);

        if (heightTotle == 0) {
            return;
        }
        if (mState == STATE_REFRESHING) {
            return;
        }

        float textHight = mHintTextView.getMeasuredHeight();
        //透明
        float alpha = ((float)height) / heightTotle;

        mHintTextView.setAlpha(alpha);

        if (height < heightTotle) {

            int convert1 = DisplayUtil.dip2px(getContext(), 25);

            //高度
            RelativeLayout.LayoutParams mHintTextViewLayoutParams = (RelativeLayout.LayoutParams)mHintTextView.getLayoutParams();
            mHintTextViewLayoutParams.bottomMargin = (int)((height - textHight) / 2);
            mHintTextView.setLayoutParams(mHintTextViewLayoutParams);

            float imageViewHight = mArrowTextView.getMeasuredHeight();
            RelativeLayout.LayoutParams mArrowTextViewLayoutParams = (RelativeLayout.LayoutParams)mArrowTextView.getLayoutParams();
            mArrowTextViewLayoutParams.bottomMargin = (int)((height - imageViewHight) / 2);
            mArrowTextViewLayoutParams.topMargin = (int)((imageViewHight - height) / 2) - convert1;
            mArrowTextView.setLayoutParams(mArrowTextViewLayoutParams);


            int i = height * pullRefreshDrawable.size() / heightTotle;//0-46
            if (i >= 0 && i < pullRefreshDrawable.size()) {//0-45
                mArrowTextView.setImageResource(pullRefreshDrawable.get(i));//
            }

            RelativeLayout.LayoutParams mImageViewLayoutParams = (RelativeLayout.LayoutParams)mImageView.getLayoutParams();
            mImageViewLayoutParams.bottomMargin = (int)((heightTotle - imageViewHight) / 2);
            mImageViewLayoutParams.topMargin = (int)((imageViewHight - heightTotle) / 2) - convert1;
            mImageView.setLayoutParams(mImageViewLayoutParams);
        }
    }

    public int getVisiableHeight() {
        return mContainer.getLayoutParams().height;
    }

}
