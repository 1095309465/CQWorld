package com.cqworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cqworld.entity.ViewStatus;
import com.cqworld.util.CQUtils;
import com.cqworld.util.http.QuarterAsyncHttp;
import com.cqworld.view.LoadDataView;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.HashMap;

public class MainActivity extends BaseActivity {
    private LoadDataView loadDataView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDataView = CQUtils.initLoadDataView(this, findViewById(R.id.parent_layout), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loadDataView.changeStatusView(ViewStatus.START);
        QuarterAsyncHttp.post("http://www.sina.com", new RequestParams(new HashMap<String, String>()), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("-------","--------onFailure--------"+responseString);
                loadDataView.changeStatusView(ViewStatus.FAILURE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("-------","--------onSuccess--------"+responseString);
                loadDataView.changeStatusView(ViewStatus.SUCCESS);
            }

            @Override
            public void onLoginInvalid(String message) {

            }
        });
    }
}
