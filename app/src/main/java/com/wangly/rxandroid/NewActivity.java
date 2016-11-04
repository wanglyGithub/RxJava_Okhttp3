package com.wangly.rxandroid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wangly.rxandroid.constant.Constant;
import com.wangly.rxandroid.presenter.LoginUtils;
import com.wangly.rxandroid.view.LoginView;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxAndroid 响应式开发！
 * RxJava + Okhttp3 处理网络响应事件！
 */

public class NewActivity extends AppCompatActivity implements LoginView {

    private EditText et_userName;
    private EditText et_userPassword;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("登录中····");
        et_userName = (EditText) findViewById(R.id.et_username);
        et_userPassword = (EditText) findViewById(R.id.et_userpassword);
    }


    public void newLogin(View view) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("username", getUserName());
        params.put("password", getUserPasswold());
        LoginUtils.getInstance().login("", params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissDialog();
                        showTost(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("NewActiviy1", "登陆成功：" + s);

                        Toast.makeText(NewActivity.this, "登陆成功!可以跳转界面了", Toast.LENGTH_LONG).show();
                    }
                });
    }


    public void getUserInfo(View view) {
        showProgress();
        LoginUtils.getInstance().getUserInfo(Constant.testUrl + "MemberInfo/UserInfo/userid/40").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissDialog();
                showTost(e.getLocalizedMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i("NewActivity", "获取用户信息成功：" + s);
            }
        });
    }


    @Override
    public String getUserName() {
        return et_userName.getText().toString().trim();
    }

    @Override
    public String getUserPasswold() {
        return et_userPassword.getText().toString().trim();
    }

    @Override
    public void showTost(String str) {
        Toast.makeText(this, "异常信息内容:" + "\n" + str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void dismissDialog() {
        progressDialog.dismiss();
    }
}
