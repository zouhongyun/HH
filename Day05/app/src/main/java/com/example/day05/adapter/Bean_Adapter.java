package com.example.day05.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day05.MainActivity;
import com.example.day05.R;
import com.example.day05.baen.Bean;
import com.example.day05.callback.Onclicklenter;

import java.util.ArrayList;

import io.reactivex.internal.queue.MpscLinkedQueue;
import retrofit2.http.POST;

public class Bean_Adapter extends RecyclerView.Adapter {
    private final Context context;
    private ArrayList<Bean.ResultsBean> list;
    private ArrayList<Boolean> booleans;
    private Onclicklenter onclicklenter;
    private boolean b =false;

    public void setOnclicklenter(Onclicklenter onclicklenter) {
        this.onclicklenter = onclicklenter;
    }


    public Bean_Adapter(Context context, ArrayList<Bean.ResultsBean> list, ArrayList<Boolean> booleans) {
        this.context = context;
        this.list = list;
        this.booleans = booleans;
    }
    public void setFlags(boolean b){
        this.b = b;
    }
    public void setFlags( ArrayList<Bean.ResultsBean> list, ArrayList<Boolean> booleans){
        this.list = list;
        this.booleans = booleans;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_bean, null, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyHolder viewHolder1= (MyHolder) viewHolder;
        if (b){
            viewHolder1.rb.setVisibility(View.VISIBLE);
        }else {
            viewHolder1.rb.setVisibility(View.GONE);
        }
        RequestOptions requestOptions = new RequestOptions()
                .circleCrop();
        Glide.with(context).load(list.get(i).getUrl()).apply(requestOptions).into(viewHolder1.img);
        viewHolder1.content.setText(list.get(i).getSource());
        viewHolder1.rb.setChecked(booleans.get(i));
        viewHolder1.rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleans.set(i,isChecked);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView content;CheckBox rb;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.bean_img);
            content = itemView.findViewById(R.id.bean_content);
            rb = itemView.findViewById(R.id.rb);
        }
    }
}
