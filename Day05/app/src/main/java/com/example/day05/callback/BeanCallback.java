package com.example.day05.callback;

import com.example.day05.baen.Bean;

public interface BeanCallback {
    void BeanSeccess(Bean bean);
    void BeanFail(String string);
}
