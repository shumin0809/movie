package com.shumin.movie.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.rest.RestClient;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movie = (TextView) findViewById(R.id.movie);

        Map<String, String> params = new LinkedHashMap<>();
        params.put("t", "batman");
        params.put("plot", "short");
        params.put("r", "json");
        params.put("y", "2016");
        Call<Movie> call = RestClient.getClient().searchMovie(params);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    movie.setText(response.body().Title);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}
