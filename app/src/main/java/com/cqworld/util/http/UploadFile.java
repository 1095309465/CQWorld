package com.cqworld.util.http;

import android.content.Context;
import android.os.Build;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.TextHttpResponseHandler;
import com.cqworld.BuildConfig;

import org.apache.http.Header;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

/**
 * Created by Administrator on 2016/9/30.
 */
public class UploadFile {
    private static AsyncHttpClient uploadAsyncHttpClient = new AsyncHttpClient();

    static {
        uploadAsyncHttpClient.setTimeout(60000);
        uploadAsyncHttpClient.addHeader("Accept", "text/html;charset=UTF-8,application/json");
        String userAgent = System.getProperty("http.agent", Build.FINGERPRINT);

    }
    public static void uploadPicture(final Context context, String st, String type, File picture) {
        RequestParams params = new RequestParams();

        params.put("type", type);
        try {
            params.put("photoone", picture, "image/*");
            params.put("filename", UUID.randomUUID()+".jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        uploadAsyncHttpClient.post(context, HttpRequest.UPLOAD_PIC_URL, params, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//
//            }
//
//            @Override
//            public void onLoginInvalid(String message) {
//
//            }
//        });

    }

}
