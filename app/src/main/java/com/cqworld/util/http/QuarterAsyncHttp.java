package com.cqworld.util.http;

import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.message.BasicHeader;
import org.simple.eventbus.EventBus;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;
import com.cqworld.BuildConfig;
import com.cqworld.util.TempData;


public class QuarterAsyncHttp {

	public static final int RETRY_COUNT = 5;

	private static final String HTTP_ACCEPT = "text/html;charset=UTF-8,application/json";
	private static final String HTTP_CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
	private static final int HTTP_REQUEST_TIMEOUT = 20000;
	private static final int HTTP_MAX_CONNECTIONS = 30;
	private static final String HTTP_CONNECTION = "close";


	private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80, 443);

	static {

		asyncHttpClient.setTimeout(HTTP_REQUEST_TIMEOUT);
		asyncHttpClient.setResponseTimeout(HTTP_REQUEST_TIMEOUT);
		asyncHttpClient.setConnectTimeout(HTTP_REQUEST_TIMEOUT);

		asyncHttpClient.setMaxConnections(HTTP_MAX_CONNECTIONS);
		asyncHttpClient.addHeader("Accept", HTTP_ACCEPT);
		asyncHttpClient.addHeader("Content-Type", HTTP_CONTENT_TYPE);
		asyncHttpClient.addHeader("Connection", HTTP_CONNECTION);
		asyncHttpClient.addHeader("User-Agent", "");
	}

	public static void post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
		asyncHttpClient.post(url, params, responseHandler);
	}

	public static void post(String url, ResponseHandlerInterface responseHandler) {
		asyncHttpClient.post(url, responseHandler);
	}

	public static void post(final Context context, final String url,  final HashMap<String, String> hashMap,
			final TextHttpResponseHandler textHttpResponseHandler) {
		post(context, url, hashMap, textHttpResponseHandler, 0);
	}

	public static void post(final Context context, final String url,  final HashMap<String, String> hashMap,
			final TextHttpResponseHandler textHttpResponseHandler, final int retryCount) {

		final RequestParams requestParams = (null != hashMap && hashMap.size() > 0) ? new RequestParams(hashMap) : null;

		asyncHttpClient.post(context, url, requestParams, new TextHttpResponseHandler() {

			@Override
			public void onLoginInvalid(String message) {
				textHttpResponseHandler.onLoginInvalid(message);
			}

			@Override
			public void onFailure(int statusCode, Header[] responseHeaders, String responseString, Throwable throwable) {


				if (retryCount < RETRY_COUNT ) {
					final int requestCount = retryCount + 1;

					post(context, url, hashMap, textHttpResponseHandler, requestCount);

				} else {
					textHttpResponseHandler.onFailure(statusCode, responseHeaders, responseString, throwable);
				}
			}

			@Override
			public void onSuccess(int statusCode, Header[] responseContentHeaders, String responseString) {
			


				if (statusCode == HttpStatus.SC_OK) {
					textHttpResponseHandler.onSuccess(statusCode, responseContentHeaders, responseString);

				}

			}
		});
	}

	public static void post(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
		asyncHttpClient.post(context, url, params, responseHandler);
	}

	/**
	 * 取消http请求
	 * 
	 * @param context
	 */
	public static void cancelRequests(Context context) {
		if (null != context) {
			asyncHttpClient.cancelRequests(context, true);
		}
	}


	private static class ResultData {

		@Expose
		@SerializedName("success")
		boolean success;

		@Expose
		@SerializedName("code")
		String code;
	}


	
}
