package com.friendtimes.http.request;


import com.friendtimes.http.callback.Callback;
import com.friendtimes.http.utils.Exceptions;
import com.mistyrain.okhttp.Headers;
import com.mistyrain.okhttp.Request;
import com.mistyrain.okhttp.RequestBody;

import java.util.Map;


/**
 * Created by wutao on 2016/2/1.
 * 基本请求抽象基类，主要封装 不同请求类型的公共
 */
public abstract class OKHttpRequest {
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;


    protected Request.Builder builder = new Request.Builder();

    protected OKHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;

        if (url == null) {
            Exceptions.illegalArgument("url can not be null.");
        }
    }


    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return requestBody;
    }

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public RequestCall build() {
        return new RequestCall(this);
    }


    public Request generateRequest(Callback callback) {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(builder, requestBody);
    }


    private void prepareBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }


    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    @Override
    public String toString() {
        return "OkHttpRequest{" +
                  "url='" + url + '\'' +
                  ", tag=" + tag +
                  ", params=" + params +
                  ", headers=" + headers +
                  '}';
    }

}
