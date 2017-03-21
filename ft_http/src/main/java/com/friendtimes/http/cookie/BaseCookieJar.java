package com.friendtimes.http.cookie;

import com.mistyrain.okhttp.Cookie;
import com.mistyrain.okhttp.CookieJar;
import com.mistyrain.okhttp.HttpUrl;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wutao on 2016/2/1.
 * 基本cookie
 */
public class BaseCookieJar implements CookieJar {
    private final List<Cookie> allCookies = new ArrayList<>();

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        allCookies.addAll(cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result = new ArrayList<>();
        for (Cookie cookie : allCookies) {
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }
}
