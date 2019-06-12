package com.example.day05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.day05.adapter.Bean_Adapter;
import com.example.day05.baen.Bean;
import com.example.day05.callback.Onclicklenter;
import com.example.day05.model.BeanModelimple;
import com.example.day05.persenter.BeanPerimple;
import com.example.day05.view.BeanView;

import java.util.ArrayList;

//邹鸿运 1810B
public class MainActivity extends AppCompatActivity implements View.OnClickListener, BeanView {

    private RecyclerView mRv;
    /**
     * 操作
     */
    private Button mBtnOperate;
    /**
     * 删除
     */
    private Button mBtnDelete;
    /**
     * 完成
     */
    private Button mBtnFinish;
    private ArrayList<Bean.ResultsBean> list;
    private Bean_Adapter adapter;
    private ArrayList<Integer> integers;
    private ArrayList<Boolean> booleans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        booleans = new ArrayList<>();
        initData();
        initView();
    }

    private void initData() {
        BeanPerimple beanPerimple = new BeanPerimple(new BeanModelimple(), this);
        beanPerimple.getdata();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mBtnOperate = (Button) findViewById(R.id.btn_operate);
        mBtnOperate.setOnClickListener(this);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(this);
        mBtnFinish = (Button) findViewById(R.id.btn_finish);
        mBtnFinish.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        adapter = new Bean_Adapter(this,list,booleans);
        mRv.setAdapter(adapter);
        integers = new ArrayList<>();
//        adapter.setOnclicklenter(new Onclicklenter() {
//            @Override
//            public void Onclick(int position, View view) {
//                integers.add(position);
//                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_operate:
                adapter.setFlags(true);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete:
                for (int i = 0; i < booleans.size(); i++) {
                    if (booleans.get(i)) {
                        booleans.remove(i);
                        list.remove(i);
                        i--;
                    }
                }
                adapter.setFlags(list, booleans);
                adapter.notifyDataSetChanged();
                break;
                case R.id.btn_finish:
                    adapter.setFlags(false);
                    adapter.notifyDataSetChanged();
                break;
        }
    }



    @Override
    public void BeanSeccess(Bean bean) {
        list.addAll(bean.getResults());
        for (int i = 0; i < list.size(); i++) {
            booleans.add(false);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void BeanFail(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
