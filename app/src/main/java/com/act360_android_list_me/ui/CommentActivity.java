package com.act360_android_list_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.act360_android_list_me.R;
import com.act360_android_list_me.adapter.CommentRecyclerAdapter;
import com.act360_android_list_me.controller.RestManager;
import com.act360_android_list_me.pojo.CommentPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommentActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    CommentRecyclerAdapter commentRecyclerAdapter;
    RestManager restManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id", 0);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewComment);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        commentRecyclerAdapter = new CommentRecyclerAdapter();
        recyclerView.setAdapter(commentRecyclerAdapter);

        restManager = new RestManager();

        Call<List<CommentPojo>> listCall = restManager.getApiService().getIdComments(id);

        listCall.enqueue(new Callback<List<CommentPojo>>() {
            @Override
            public void onResponse(Call<List<CommentPojo>> call, Response<List<CommentPojo>> response) {

                if (response.isSuccessful()){

                    List<CommentPojo> commentPojo = response.body();

                    for (int i=0; i < commentPojo.size(); i++){

                        CommentPojo pojo = commentPojo.get(i);
                        commentRecyclerAdapter.addData(pojo);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CommentPojo>> call, Throwable t) {

            }
        });
    }
}
