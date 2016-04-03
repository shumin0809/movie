package com.shumin.movie.rest;

import com.shumin.movie.constant.Constants;
import com.shumin.movie.model.Movie;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by shumin on 4/3/16.
 */
public interface RetrofitApi {

    @GET(Constants.SEARCH)
    Call<Movie> searchMovie(@QueryMap Map<String, String> params);

}
