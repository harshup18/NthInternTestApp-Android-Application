package com.example.myapplication;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {
    // use PageKeyedDataSource since parameters are given in form of page details
    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 2;
    private static final String SITE_NAME = "stackoverflow";


    // Load the items for 1st time
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getUsers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiR>() {
                    @Override
                    public void onResponse(Call<StackApiR> call, Response<StackApiR> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiR> call, Throwable t) {

                    }
                });
    }

    //Load previous objects
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getUsers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiR>() {
                    @Override
                    public void onResponse(Call<StackApiR> call, Response<StackApiR> response) {

                        if (response.body() != null) {
                            Integer adkey = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().items, adkey);
                        }

                    }

                    @Override
                    public void onFailure(Call<StackApiR> call, Throwable t) {

                    }
                });
    }

    //Load next objects from api
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getUsers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiR>() {
                    @Override
                    public void onResponse(Call<StackApiR> call, Response<StackApiR> response) {

                        if (response.body() != null) {
                            Integer adkey = response.body().has_more ? params.key + 1 : null;
                            callback.onResult(response.body().items, adkey);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiR> call, Throwable t) {

                    }
                });
    }
}
