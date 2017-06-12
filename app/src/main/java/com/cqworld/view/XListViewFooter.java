/**
 * @file XFooterView.java
 * @create Mar 31, 2012 9:33:43 PM
 * @author Maxwin
 * @description XListView's footer
 */
package com.cqworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cqworld.R;


public class XListViewFooter extends LinearLayout {
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_LOADING = 2;

	private Context mContext;

	private View mContentView;
	private TextView mHintView;
	
	private ImageView mImageView;
	
	private Animation mImageViewAnimation;
	
	public XListViewFooter(Context context) {
		super(context);
		initView(context);
	}
	
	public XListViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	
	public void setState(int state) {
		mHintView.setVisibility(View.INVISIBLE);
		mImageView.setVisibility(View.VISIBLE);
		mHintView.setVisibility(View.INVISIBLE);
		if (state == STATE_READY) {
			mHintView.setVisibility(View.VISIBLE);
			mImageView.setVisibility(View.INVISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_ready);
//			start();
		} else if (state == STATE_LOADING) {
			mImageView.setVisibility(View.INVISIBLE);
			start();
		} else {
			mHintView.setVisibility(View.VISIBLE);
			mImageView.setVisibility(View.INVISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_normal);
			stop();
		}
	}
	
	public void setBottomMargin(int height) {
		if (height < 0) return ;
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.bottomMargin = height;
		mContentView.setLayoutParams(lp);
	}
	
	public int getBottomMargin() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		return lp.bottomMargin;
	}
	
	
	/**
	 * normal status
	 */
	public void normal() {
		mHintView.setVisibility(View.VISIBLE);
		mImageView.setVisibility(View.INVISIBLE);
	}
	
	
	/**
	 * loading status 
	 */
	public void loading() {
		mHintView.setVisibility(View.GONE);
		mImageView.setVisibility(View.VISIBLE);
		
	}
	
	/**
	 * hide footer when disable pull load more
	 */
	public void hide() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.height = 0;
		mContentView.setLayoutParams(lp);
	}
	
	/**
	 * show footer
	 */
	public void show() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(lp);
	}
	
	private void initView(Context context) {
		mContext = context;
		LinearLayout moreView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.xlistview_footer, null);
		addView(moreView);
		moreView.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		
		mContentView = moreView.findViewById(R.id.xlistview_footer_content);
		mHintView = (TextView)moreView.findViewById(R.id.xlistview_footer_hint_textview);
		mImageView = (ImageView) findViewById(R.id.xlistview_footer_progressbar);
		
		mImageViewAnimation = AnimationUtils.loadAnimation(context, R.anim.rote);
		mImageViewAnimation.setInterpolator(new LinearInterpolator());
		mImageViewAnimation.setDuration(560);
		mImageViewAnimation.setRepeatCount(Animation.INFINITE);
		mImageViewAnimation.setRepeatMode(Animation.RESTART);
	}
	
	public void stop() {
		mImageView.clearAnimation();
	}

	public void start() {
		stop();
		mImageView.startAnimation(mImageViewAnimation);
	}
	
	
}

