package com.act360_android_list_me.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.act360_android_list_me.R;
import com.act360_android_list_me.pojo.ListPojo;

import java.util.ArrayList;
import java.util.List;


public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.RecyclerViewHolder> {

    List<ListPojo> listPojo;
    private final ListClickListner clickListner;

    public ListRecyclerAdapter(ListClickListner listner) {
        listPojo = new ArrayList<>();
        clickListner = listner;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ListPojo currListPojo = listPojo.get(position);
        holder.userId.setText(Integer.toString(currListPojo.getUserId()));
        holder.id.setText(Integer.toString(currListPojo.getId()));
        holder.title.setText(currListPojo.getTitle());
        holder.body.setText(currListPojo.getBody());
    }

    @Override
    public int getItemCount() {
        return listPojo.size();
    }

    public void addData(ListPojo pojo) {
        listPojo.add(pojo);
        notifyDataSetChanged();
    }

    public ListPojo getSelectedList(int position) {
        return listPojo.get(position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView userId, id, title, body;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            userId = (TextView) itemView.findViewById(R.id.txtUserId);
            id = (TextView) itemView.findViewById(R.id.txtId);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            body = (TextView) itemView.findViewById(R.id.txtBody);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListner.onClick(getLayoutPosition());
        }
    }

    public interface ListClickListner {
        void onClick(int position);
    }
}
