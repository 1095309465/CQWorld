package com.cqworld.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cqworld.R;
import com.cqworld.entity.ViewStatus;


public class LoadDataView extends FrameLayout {

	/** 数据加载异常 */
	private final View errorView;
	/** 没有数据 */
	private final View noDataView;
	/** 数据 */
	private final View dataView;
	/** 加载数据中 */
	private final View loadingView;
	/** 网络连接失败 */
	private final View netErrorView;
	private final LayoutInflater inflater;
	private final ImageView loadingImg;
	private final Animation mImageViewAnimation;
	private volatile boolean isFirstLoad = true;

	public LoadDataView(Context context, View view) {
		super(context);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dataView = view;
		inflater = LayoutInflater.from(context);
		netErrorView = inflater.inflate(R.layout.layout_net_error, null);
		errorView = inflater.inflate(R.layout.layout_data_error, null);
		noDataView = inflater.inflate(R.layout.layout_data_empty, null);
		loadingView = inflater.inflate(R.layout.layout_data_loading, null);
		loadingImg = (ImageView) loadingView.findViewById(R.id.loading_img);

		mImageViewAnimation = AnimationUtils.loadAnimation(context, R.anim.rote);
		mImageViewAnimation.setInterpolator(new LinearInterpolator());
		mImageViewAnimation.setDuration(2000);
		mImageViewAnimation.setRepeatCount(Animation.INFINITE);
		mImageViewAnimation.setRepeatMode(Animation.RESTART);
		initViews();
	}

	private void initViews() {
		if (null != dataView) {
			addView(dataView);
		}
		if (null != errorView) {
			addView(errorView);
			errorView.setVisibility(View.GONE);
		}
		if (null != netErrorView) {
			addView(netErrorView);
			netErrorView.setVisibility(View.GONE);
		}
		if (null != loadingView) {
			addView(loadingView);
			loadingView.setVisibility(View.GONE);
		}
		if (null != noDataView) {
			addView(noDataView);
			noDataView.setVisibility(View.GONE);
		}
	}

	public void stop() {
		if (null != loadingImg) {
			loadingImg.clearAnimation();
		}
	}

	public void start() {
		stop();
		if (null != loadingImg) {

			loadingImg.startAnimation(mImageViewAnimation);
		}
	}

	/**
	 * 开始加载
	 * 
	 * <br>
	 * 创建时间：2015-7-31 下午3:00:43
	 */
	public void loadStart() {
		if (null != dataView && dataView.getVisibility() != View.GONE) {
			dataView.setVisibility(View.GONE);
		}
		if (null != errorView && errorView.getVisibility() != View.GONE) {
			errorView.setVisibility(View.GONE);
		}
		if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
			netErrorView.setVisibility(View.GONE);
		}
		if (null != noDataView && noDataView.getVisibility() != View.GONE) {
			noDataView.setVisibility(View.GONE);
		}

		if (null != loadingView && loadingView.getVisibility() != View.VISIBLE) {
			start();
			loadingView.setVisibility(View.VISIBLE);

		}
	}

	/**
	 * 加载成功
	 * 
	 * <br>
	 * 创建时间：2015-7-31 下午3:00:33
	 */
	public void loadSuccess() {
		stop();
		if (null != dataView && dataView.getVisibility() != View.VISIBLE) {
			dataView.setVisibility(View.VISIBLE);
		}
		if (null != errorView && errorView.getVisibility() != View.GONE) {
			errorView.setVisibility(View.GONE);
		}
		if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
			netErrorView.setVisibility(View.GONE);
		}
		if (null != noDataView && noDataView.getVisibility() != View.GONE) {
			noDataView.setVisibility(View.GONE);
		}

		if (null != loadingView && loadingView.getVisibility() != View.GONE) {
			loadingView.setVisibility(View.GONE);
		}
	}

	/**
	 * 加载失败
	 * 
	 * <br>
	 * 创建时间：2015-7-31 下午3:00:53
	 */
	public void loadError() {
		stop();
		if (null != dataView && dataView.getVisibility() != View.GONE) {
			dataView.setVisibility(View.GONE);
		}
		if (null != errorView && errorView.getVisibility() != View.VISIBLE) {
			errorView.setVisibility(View.VISIBLE);
		}
		if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
			netErrorView.setVisibility(View.GONE);
		}
		if (null != noDataView && noDataView.getVisibility() != View.GONE) {
			noDataView.setVisibility(View.GONE);
		}

		if (null != loadingView && loadingView.getVisibility() != View.GONE) {
			loadingView.setVisibility(View.GONE);
		}
	}

	/**
	 * 加载成功，但无数据
	 * 
	 * <br>
	 * 创建时间：2015-7-31 下午3:01:03
	 */
	public void loadNoData() {
		stop();
		if (null != dataView && dataView.getVisibility() != View.GONE) {
			dataView.setVisibility(View.GONE);
		}
		if (null != errorView && errorView.getVisibility() != View.GONE) {
			errorView.setVisibility(View.VISIBLE);
		}
		if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
			netErrorView.setVisibility(View.GONE);
		}
		if (null != noDataView && noDataView.getVisibility() != View.VISIBLE) {
			noDataView.setVisibility(View.GONE);
		}
		if (null != loadingView && loadingView.getVisibility() != View.GONE) {
			loadingView.setVisibility(View.GONE);
		}
	}

	/**
	 * 网络连接问题，加载异常，检查网络，点击屏幕重新连接
	 * 
	 * <br>
	 * 创建时间：2015-7-31 下午3:01:26
	 */
	public void loadNotNetwork() {
		stop();

		if (null != dataView && dataView.getVisibility() != View.GONE) {
			dataView.setVisibility(View.GONE);
		}
		if (null != errorView && errorView.getVisibility() != View.GONE) {
			errorView.setVisibility(View.GONE);
		}
		if (null != netErrorView && netErrorView.getVisibility() != View.VISIBLE) {
			netErrorView.setVisibility(View.VISIBLE);
		}
		if (null != noDataView && noDataView.getVisibility() != View.GONE) {
			noDataView.setVisibility(View.GONE);
		}

		if (null != loadingView && loadingView.getVisibility() != View.GONE) {
			loadingView.setVisibility(View.GONE);
		}
	}

	public void setErrorListner(OnClickListener listener) {
		if (null == listener) {
			return;
		}
		if (null != errorView) {

			errorView.setOnClickListener(listener);
		}
		if (null != netErrorView) {

			netErrorView.setOnClickListener(listener);
		}
		if (null != noDataView) {

			noDataView.setOnClickListener(listener);
		}

	}

	public void changeStatusView(ViewStatus status) {
		if (isFirstLoad) {
			switch (status) {
			case START:
				loadStart();
				break;
			case SUCCESS:
				isFirstLoad = false;
				loadSuccess();
				break;
			case FAILURE:
				loadError();
				break;
			case EMPTY:
				loadNoData();
				break;
			case NOTNETWORK:
				loadNotNetwork();
				break;
			}
		}
	}

	public void setFirstLoad(){
		this.isFirstLoad = true;
	}
}
