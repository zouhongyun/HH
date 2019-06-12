package com.example.two.model;

import com.example.two.api.MyServer;
import com.example.two.bean.ListBean;
import com.example.two.callback.ListCallback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListModelimple implements ListModel {
    @Override
    public void getdata(final ListCallback listCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<ListBean> listBeanObservable = myServer.get();
        listBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        if (listBean!=null) {
                            listCallback.ListSeccess(listBean);
                        }else {
                            listCallback.ListFail("失败");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        listCallback.ListFail(e.getMessage());


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
