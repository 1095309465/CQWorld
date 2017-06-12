package com.cqworld.util.http.callback;

public interface ValidateCallback extends BaseRequestCallback {

	/**
	 * 数据请求失败(需要重新登录)
	 * 
	 * @param message
	 */
	void loginInvalid(String message);
}
