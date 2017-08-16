package com.example.lps.superplayer.api;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 16:25
 */


public interface ApiCallBack<T> {
    void onsuccess(T data);
    void onFail();
}
