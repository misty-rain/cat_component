package com.friendtimes.http.request;


import com.friendtimes.http.utils.Exceptions;
import com.mistyrain.okhttp.MediaType;
import com.mistyrain.okhttp.Request;
import com.mistyrain.okhttp.RequestBody;

import java.util.Map;



/**
 * Created by wutao on 2016/2/1.
 * 一般post 请求，通过content 发送字符串
 */
public class OKHttpPostStringRequest extends OKHttpRequest {

    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");
    private String content;
    private MediaType mediaType;

    public OKHttpPostStringRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, String content, MediaType mediaType) {
        super(url, tag, params, headers);
        this.content = content;
        this.mediaType = mediaType;

        if (this.content == null) {
            Exceptions.illegalArgument("the content can not be null !");
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }

    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mediaType, content);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    @Override
    public String toString() {
        return super.toString() + ", requestBody{content=" + content + "} ";
    }


}
