package com.cqworld.util.http;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpStatus;



import android.content.Context;
import android.text.TextUtils;


import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.cqworld.protocol.BaseProtocol;
import com.cqworld.util.TempData;
import com.cqworld.util.http.callback.BaseRequestCallback;


public class AsyncHttpUtils {


	
	public static void loadData(final Context context, String requestUrl, HashMap<String, String> params, final BaseRequestCallback baseRequestCallback) {
		QuarterAsyncHttp.post(context, requestUrl, params, new TextHttpResponseHandler() {

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				baseRequestCallback.failure("数据请求出错...");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if (statusCode == HttpStatus.SC_OK) {
					BaseProtocol baseProtocol = null;
					try {
						baseProtocol = TempData.getGson().fromJson(responseString, BaseProtocol.class);
					} catch (JsonSyntaxException e) {
					}

					if (null != baseProtocol) {
						if(!TextUtils.isEmpty(baseProtocol.code)){
							if (baseProtocol.code.equals("1")) {
								baseRequestCallback.success(responseString);
							} else {
								baseRequestCallback.failure(baseProtocol.msg);
							}
						}else{
							if(baseProtocol.success){
								baseRequestCallback.success(responseString);
							}else{
								baseRequestCallback.failure(baseProtocol.msg);
							}
						}

					} else {
						baseRequestCallback.failure("解析数据出错...");
					}
				} else {
					baseRequestCallback.failure("数据返回出错...");
				}
			}

			@Override
			public void onLoginInvalid(String message) {
			}

			
		});
	}



	
}
