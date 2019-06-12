package com.example.two.api;

import com.example.two.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyServer {
    String URL="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<ListBean> get();
}
