package com.cqworld.util.http;


import com.cqworld.BuildConfig;

/**
 * Created by Administrator on 2016/9/26.
 */
public class BaseHttpRequest {
    private static boolean isLive = BuildConfig.QUARTER_LIVE;

    private static final String QUARTER_BASE_LIVE_URL = "http://182.254.221.252:8090/qtlover";
    private static final String QUARTER_BASE_TEST_URL = "http://182.254.221.252/qtlover";


}
