package com.act360_android_list_me.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.act360_android_list_me.R;
import com.act360_android_list_me.pojo.CommentPojo;

import java.util.ArrayList;
import java.util.List;


public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.RecyclerViewHolder> {

    List<CommentPojo> commentPojo;

    public CommentRecyclerAdapter() {
        commentPojo = new ArrayList<>();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_items, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        CommentPojo currCommentPojo = commentPojo.get(position);
        holder.postId.setText(Integer.toString(currCommentPojo.getPostId()));
        holder.id.setText(Integer.toString(currCommentPojo.getId()));
        holder.name.setText(currCommentPojo.getName());
        holder.email.setText(currCommentPojo.getEmail());
        holder.body.setText(currCommentPojo.getBody());
    }

    @Override
    public int getItemCount() {
        return commentPojo.size();
    }

    public void addData(CommentPojo pojo) {
        commentPojo.add(pojo);
        notifyDataSetChanged();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView postId, id, name, email, body;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            postId = (TextView) itemView.findViewById(R.id.txtPostId);
            id = (TextView) itemView.findViewById(R.id.txtcId);
            name = (TextView) itemView.findViewById(R.id.txtName);
            email = (TextView) itemView.findViewById(R.id.txtEmail);
            body = (TextView) itemView.findViewById(R.id.txtcBody);
        }
    }
}
