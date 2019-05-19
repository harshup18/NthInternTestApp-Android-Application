package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    // Defining Api parameters
    @GET("users")
    Call<StackApiR> getUsers(
            @Query("page") int page,
            @Query("pagesize") int size,
            @Query("site") String site
    );

}
