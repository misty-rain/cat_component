package com.friendtimes.http.callback;

import com.mistyrain.okhttp.Call;
import com.mistyrain.okhttp.Response;

import java.io.IOException;

/**
 * Created by wutao on 2016/2/1.
 * 默认回调泛型为string，
 */
public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onError(Call call, Response response, Exception e) {

    }
}
