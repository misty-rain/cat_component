package com.friendtimes.http.request;

import com.mistyrain.okhttp.Request;
import com.mistyrain.okhttp.RequestBody;

import java.util.Map;



/**
 * Created by wutao on 2016/2/1.
 * get 请求
 */
public class OKHttpGetRequest extends OKHttpRequest {
    public OKHttpGetRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers) {
        super(url, tag, params, headers);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.get().build();
    }

}
