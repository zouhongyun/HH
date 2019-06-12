package com.example.day05.api;

import com.example.day05.baen.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyServer {
    String URL="https://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/10/1")
    Observable<Bean> get();
}
