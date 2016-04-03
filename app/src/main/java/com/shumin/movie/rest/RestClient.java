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
//            OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request().newBuilder()
//                                    .addHeader("Accept", "Application/JSON").build();
//                            return chain.proceed(request);
//                        }
//                    }).build();
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofitApi = client.create(RetrofitApi.class);
        }
        return retrofitApi;
    }
}
