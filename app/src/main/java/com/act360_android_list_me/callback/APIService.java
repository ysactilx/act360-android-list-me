package com.act360_android_list_me.callback;

import com.act360_android_list_me.pojo.CommentPojo;
import com.act360_android_list_me.pojo.ListPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIService {

    @GET("posts")
    Call<List<ListPojo>> getAllList();

    @GET("posts/{id}/comments")
    Call<List<CommentPojo>> getIdComments(@Path("id") int id);

}
