package com.cqworld;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

public class BaseActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			EventBus.getDefault().register(this);
		} catch (NoClassDefFoundError ignored) {
		}
	}

	protected void setTopBackListener() {
		ImageView topLeftImageView = (ImageView) findViewById(R.id.top_left_imageview);
		if (topLeftImageView != null) {
			topLeftImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}
	protected void setTopTitle(int stringId) {
		TextView titleTextView = (TextView) findViewById(R.id.titletextview);
		if (titleTextView != null) {
			titleTextView.setText(getString(stringId));
		}
	}
	
	protected void setTopTitle(String title) {
		TextView titleTextView = (TextView) findViewById(R.id.titletextview);
		if (titleTextView != null) {
			titleTextView.setText(title);
		}
	}
	private ProgressDialog progress;

	synchronized protected void dialogShow() {
		try {
			if (null == progress) {
				progress = new ProgressDialog(this);
				progress.setMessage("请稍候……");
				progress.setIndeterminate(true);
				progress.setCancelable(false);
				progress.setCanceledOnTouchOutside(false);
			}
			if (null != progress && !progress.isShowing()) { progress.show(); }
		} catch (Exception e) {
		}
	}

	synchronized protected void dialogShow(String msg) {
		try {
			if (null == progress) {
				progress = new ProgressDialog(this);
				progress.setMessage(msg);
				progress.setIndeterminate(true);
				progress.setCancelable(false);
				progress.setCanceledOnTouchOutside(false);
			}
			if (null != progress && !progress.isShowing()) { progress.show(); }
			progress.setMessage(msg);
		} catch (Exception e) {
		}
	}

	synchronized protected void dialogDismiss() {
		try {
			if (null != progress && progress.isShowing()) { progress.dismiss(); }
		} catch (Exception e) {
		}
	}
	@Subscriber(mode = ThreadMode.MAIN, tag = "com.cqworld.closeAll")
	public void closeAll(String string) {
		finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
