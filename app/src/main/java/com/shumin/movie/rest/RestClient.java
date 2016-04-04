package com.shumin.movie.rest;

import com.shumin.movie.constant.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shumin on 4/3/16.
 */
public class RestClient {

    private static RetrofitApi retrofitApi;

    public static RetrofitApi getClient() {
        if (retrofitApi == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofitApi = client.create(RetrofitApi.class);
        }
        return retrofitApi;
    }
}
