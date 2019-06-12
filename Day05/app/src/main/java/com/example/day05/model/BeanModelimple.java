package com.example.day05.model;

import com.example.day05.api.MyServer;
import com.example.day05.baen.Bean;
import com.example.day05.callback.BeanCallback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeanModelimple implements BeanModel {
    @Override
    public void getdata(final BeanCallback beanCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<Bean> beanObservable = myServer.get();
        beanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        if (bean!=null) {
                            beanCallback.BeanSeccess(bean);
                        }else {
                            beanCallback.BeanFail("失败");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        beanCallback.BeanFail(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
