package com.friendtimes.http.request;


import com.friendtimes.http.body.CountingRequestBody;
import com.friendtimes.http.callback.Callback;
import com.friendtimes.http.config.FileInput;
import com.friendtimes.http.utils.OkHttpUtils;
import com.mistyrain.okhttp.FormBody;
import com.mistyrain.okhttp.Headers;
import com.mistyrain.okhttp.MediaType;
import com.mistyrain.okhttp.MultipartBody;
import com.mistyrain.okhttp.Request;
import com.mistyrain.okhttp.RequestBody;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


/**
 * Created by wutao on 2016/2/1.
 * post 表单请求方式，可添加多个文件
 */
public class OKHttpPostFormRequest extends OKHttpRequest {
    private List<FileInput> files;

    public OKHttpPostFormRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, List<FileInput> files) {
        super(url, tag, params, headers);
        this.files = files;
    }

    @Override
    protected RequestBody buildRequestBody() {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                  .setType(MultipartBody.FORM);
        addParams(builder);
        if (files != null) {
            if (!files.isEmpty()) {
                for (int i = 0; i < files.size(); i++) {
                    FileInput fileInput = files.get(i);
                    RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.filePath)), fileInput.file);
                    builder.addFormDataPart(fileInput.key, fileInput.filePath, fileBody);
                }
            }
        }
        return builder.build();

    }

    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        if (callback == null) return requestBody;
        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {

                OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.inProgress(bytesWritten * 1.0f / contentLength);
                    }
                });

            }
        });
        return countingRequestBody;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";

        }
        return contentTypeFor;
    }

    private void addParams(MultipartBody.Builder builder) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                          RequestBody.create(null, params.get(key)));
            }
        }
    }

    private void addParams(FormBody.Builder builder) {
        if (params == null || params.isEmpty()) {
            builder.add("1", "1");
            return;
        }

        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (files != null) {
            for (FileInput file : files) {
                sb.append(file.toString() + "  ");
            }
        }
        return sb.toString();
    }
}
