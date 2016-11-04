package com.wangly.rxandroid.view;

/**
 * Created by wangly on 2016/11/4.
 */

public interface LoginView {

    public String getUserName();

    public String getUserPasswold();

    public void showTost(String str);

    public void showProgress();

    public void dismissDialog();
}
