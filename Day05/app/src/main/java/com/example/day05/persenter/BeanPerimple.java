package com.example.day05.persenter;

import com.example.day05.baen.Bean;
import com.example.day05.callback.BeanCallback;
import com.example.day05.model.BeanModel;
import com.example.day05.view.BeanView;

public class BeanPerimple implements BeanPersenter, BeanCallback {
    private BeanModel beanModel;
    private BeanView beanView;

    public BeanPerimple(BeanModel beanModel, BeanView beanView) {
        this.beanModel = beanModel;
        this.beanView = beanView;
    }

    @Override
    public void getdata() {
        beanModel.getdata(this);

    }

    @Override
    public void BeanSeccess(Bean bean) {
        beanView.BeanSeccess(bean);
    }

    @Override
    public void BeanFail(String string) {
        beanView.BeanFail(string);
    }
}
