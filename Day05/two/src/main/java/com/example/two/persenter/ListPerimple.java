package com.example.two.persenter;

import com.example.two.bean.ListBean;
import com.example.two.callback.ListCallback;
import com.example.two.model.ListModel;
import com.example.two.view.Listview;

public class ListPerimple implements ListPerinter, ListCallback {
    private ListModel listModel;
    private Listview listview;

    public ListPerimple(ListModel listModel, Listview listview) {
        this.listModel = listModel;
        this.listview = listview;
    }

    @Override
    public void getdata() {
        listModel.getdata(this);

    }

    @Override
    public void ListSeccess(ListBean listBean) {
        listview.ListSeccess(listBean);
    }

    @Override
    public void ListFail(String string) {
        listview.ListFail(string);

    }
}
