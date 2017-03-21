package com.friendtimes.http.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mistyrain.okhttp.Call;
import com.mistyrain.okhttp.Response;


/**
 * Created by wutao on 2016/2/1.
 * 回调类型的为bitmap 对象，主要用于图片的显示
 */
public abstract class BitmapCallback extends Callback<Bitmap> {
    @Override
    public Bitmap parseNetworkResponse(Response response) throws Exception {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onError(Call call, Response response, Exception e) {

    }
}
