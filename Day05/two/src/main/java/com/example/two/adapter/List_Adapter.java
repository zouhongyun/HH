package com.example.two.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.two.MainActivity;
import com.example.two.R;
import com.example.two.bean.ListBean;
import com.example.two.callback.Onclicklenter;

import java.util.ArrayList;

public class List_Adapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<ListBean.DataBean.DatasBean> list;

    public void setOnclicklenter(Onclicklenter onclicklenter) {
        this.onclicklenter = onclicklenter;
    }

    private Onclicklenter onclicklenter;

    public List_Adapter(Context context, ArrayList<ListBean.DataBean.DatasBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_list_one, null, false);
            return new ViewHolder1(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_list_two, null, false);
            return new ViewHolder2(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType==1){
            ViewHolder1 viewHolder1= (ViewHolder1) viewHolder;
            viewHolder1.author.setText(list.get(i).getAuthor());
            viewHolder1.time.setText(list.get(i).getChapterName());
            viewHolder1.title.setText(list.get(i).getTitle());
        }else {
            ViewHolder2 viewHolder2= (ViewHolder2) viewHolder;
            viewHolder2.author1.setText(list.get(i).getAuthor());
            viewHolder2.time1.setText(list.get(i).getChapterName());
            viewHolder2.title1.setText(list.get(i).getTitle());
            Glide.with(context).load(list.get(i).getEnvelopePic()).into(viewHolder2.img);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclicklenter!=null){
                    onclicklenter.Onclick(i);
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return 1;

        }else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView author;
        private final TextView time;
        private final TextView title;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.list_one_author);
            time = itemView.findViewById(R.id.list_one_time);
            title = itemView.findViewById(R.id.list_one_title);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView author1;
        private final TextView time1;
        private final TextView title1;
        private final ImageView img;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            author1 = itemView.findViewById(R.id.list_two_author);
            time1 = itemView.findViewById(R.id.list_two_time);
            title1 = itemView.findViewById(R.id.list_two_title);
            img = itemView.findViewById(R.id.list_two_img);
        }
    }

}
