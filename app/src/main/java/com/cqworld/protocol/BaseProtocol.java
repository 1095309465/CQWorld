package com.cqworld.protocol;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class BaseProtocol {
	@Expose
	public boolean success;

	@Expose
	public String msg;

	@Expose
	public String code;

	@Expose
	public JsonElement data;

	@Override
	public String toString() {
		return "BaseProtocol [success=" + success + ", msg=" + msg + ", code="
				+ code + ", data=" + data + "]";
	}
}
