package com.example.two.callback;

import com.example.two.bean.ListBean;

public interface ListCallback {
    void ListSeccess(ListBean listBean);
    void ListFail(String string);
}
