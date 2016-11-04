package com.wangly.rxandroid.presenter;

import com.wangly.rxandroid.biz.APiService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by wangly on 2016/11/4.
 */

public class LoginUtils implements APiService {
    public final static int COMMECT_TIMEOUT = 10;
    public final static int READ_TIMEOUT = 20;

    private OkHttpClient client;

    public LoginUtils() {
        client = new OkHttpClient.Builder()
                .connectTimeout(COMMECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

    }


    public static LoginUtils getInstance() {
        return new LoginUtils();
    }


    @Override
    public Observable<String> login(String url, Map<String, String> params) {


        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {

                    if (null != params && params.isEmpty()) {
                        FormBody.Builder formBody = new FormBody.Builder();
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            formBody.add(entry.getKey(), entry.getValue());
                        }
                        RequestBody requestBody = formBody.build();
                        Request request = new Request.Builder().url(url).post(requestBody).build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    String result = response.body().string();
                                    if (!"".equals(result) && null != request) {
                                        subscriber.onNext(result);
                                    }
                                }
                                subscriber.onCompleted();
                            }

                        });
                    }

                }
            }
        });
    }


    @Override
    public Observable<String> getUserInfo(String url) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String userInfo = response.body().string();
                                if (!"".equals(userInfo) && null != userInfo) {
                                    if (userInfo.startsWith("\uFEFF")) {
                                        userInfo = userInfo.substring(1, userInfo.length());
                                    }

                                    subscriber.onNext(userInfo);
                                }
                            }
                            subscriber.onCompleted();
                        }

                    });
                }
            }
        });
    }
}
