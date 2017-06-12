package com.cqworld.util.http.callback;

public interface BaseRequestCallback {
	
	void failure(String message);

	void success(String content);
}
