package com.shumin.movie.rest;

import com.shumin.movie.model.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by shumin on 4/3/16.
 */
public interface RetrofitApi {

    @GET("/")
    Call<Result> searchMovie(@QueryMap Map<String, String> params);

}
