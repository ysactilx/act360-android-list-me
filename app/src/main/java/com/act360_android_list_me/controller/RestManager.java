package com.act360_android_list_me.controller;

import com.act360_android_list_me.callback.APIService;
import com.act360_android_list_me.helper.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RestManager {

    private APIService apiService;

    public APIService getApiService(){

        if (apiService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(APIService.class);
        }

        return apiService;
    }
}
