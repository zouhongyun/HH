package com.example.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.two.adapter.List_Adapter;
import com.example.two.bean.ListBean;
import com.example.two.callback.Onclicklenter;
import com.example.two.model.ListModelimple;
import com.example.two.persenter.ListPerimple;
import com.example.two.view.Listview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Listview {
    /**
     * 文章列表
     */
    private TextView mTvToolbar;
    private Toolbar mTl;
    private RecyclerView mRvList;
    private ArrayList<ListBean.DataBean.DatasBean> list;
    private List_Adapter adapter;

    //邹鸿运 1810B
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        initData();
        initView();
        initToolbar();
    }

    private void initData() {
        ListPerimple listPerimple = new ListPerimple(new ListModelimple(), this);
        listPerimple.getdata();
    }

    private void initToolbar() {
        mTl.setTitle("");
        setSupportActionBar(mTl);
    }

    private void initView() {
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        mTl = (Toolbar) findViewById(R.id.tl);
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(linearLayoutManager);
        adapter = new List_Adapter(this,list);
        mRvList.setAdapter(adapter);
        adapter.setOnclicklenter(new Onclicklenter() {
            @Override
            public void Onclick(int position) {

                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url",list.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    public void ListSeccess(ListBean listBean) {
        list.addAll(listBean.getData().getDatas());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void ListFail(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

    }
}
