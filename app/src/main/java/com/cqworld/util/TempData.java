package com.cqworld.util;


import com.cqworld.R;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;


public class TempData {


	private static DisplayImageOptions options;

	private static Gson gson;

	public static Gson getGson() {
		if (null == gson) {

			gson = new Gson();
		}
		return gson;
	}


	private static DisplayImageOptions userHeadDisplayImageOptions;

	public static DisplayImageOptions getUserHeadDisplayImageOptions() {
		if (null == userHeadDisplayImageOptions) {
			userHeadDisplayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.user_hear_default).showImageForEmptyUri(
					R.mipmap.user_hear_default).showImageOnFail(R.mipmap.user_hear_default).cacheInMemory(true).cacheOnDisk(true).build();
		}
		return userHeadDisplayImageOptions;
	}


}
