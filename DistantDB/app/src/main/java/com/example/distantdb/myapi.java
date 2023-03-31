package com.example.distantdb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface myapi {



        @FormUrlEncoded
        @POST("inserttask.php")
        Call<Task> adtask(@Field("task") String task);


        @GET("getalltasks.php")
        Call<List<Task>> getalltasks();
    }

