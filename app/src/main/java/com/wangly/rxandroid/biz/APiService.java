package com.wangly.rxandroid.biz;

import java.util.Map;

import rx.Observable;

/**
 * Created by wangly on 2016/11/4.
 * <p>
 * 声明被观察者的Interface
 */

public interface APiService {

    /**
     * 登陆
     */
    Observable<String> login(String url, Map<String, String> params);

    /**
     * 获取个人信息
     */
    Observable<String> getUserInfo(String url);


}
