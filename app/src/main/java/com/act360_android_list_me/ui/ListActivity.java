package com.act360_android_list_me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.act360_android_list_me.R;
import com.act360_android_list_me.adapter.ListRecyclerAdapter;
import com.act360_android_list_me.controller.RestManager;
import com.act360_android_list_me.helper.ListDatabase;
import com.act360_android_list_me.helper.Utils;
import com.act360_android_list_me.pojo.ListPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements ListRecyclerAdapter.ListClickListner {

    RecyclerView recyclerView;
    ListRecyclerAdapter listRecyclerAdapter;
    RestManager restManager;

    ListDatabase listDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        listRecyclerAdapter = new ListRecyclerAdapter(ListActivity.this);
        recyclerView.setAdapter(listRecyclerAdapter);

        restManager = new RestManager();

        listDatabase = new ListDatabase(ListActivity.this);

        if (Utils.isNetworkAvailable(getApplicationContext())){
            getFeed();
        }else {
            getFeedFromDatabase();
        }
        

    }

    @Override
    public void onClick(int position) {

        ListPojo selectedListPojo = listRecyclerAdapter.getSelectedList(position);

        int id = selectedListPojo.getId();

       /* Toast.makeText(ListActivity.this, "Position id is " + id, Toast.LENGTH_SHORT).show();*/

        Intent intent = new Intent(ListActivity.this, CommentActivity.class);
        intent.putExtra("Id", id);
        startActivity(intent);


    }

    public void getFeed() {

        Call<List<ListPojo>> listCall = restManager.getApiService().getAllList();

        listCall.enqueue(new Callback<List<ListPojo>>() {
            @Override
            public void onResponse(Call<List<ListPojo>> call, Response<List<ListPojo>> response) {

                if (response.isSuccessful()) {

                    List<ListPojo> listPojo = response.body();

                    for (int i = 0; i < listPojo.size(); i++) {
                        ListPojo pojo = listPojo.get(i);
                        listRecyclerAdapter.addData(pojo);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<ListPojo>> call, Throwable t) {

            }
        });

    }

    public void getFeedFromDatabase() {


        List<ListPojo> pojoList = listDatabase.getListPojo();

    }
}
