package com.shumin.movie.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.model.Result;
import com.shumin.movie.rest.RestClient;
import com.shumin.movie.ui.adapter.DetailAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shumin on 4/3/16.
 */
public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        String movieId = getIntent().getExtras().getString("imdbid");

        recyclerView = (RecyclerView) findViewById(R.id.detail_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Map<String, String> params = new LinkedHashMap<>();
        params.put("i", movieId);
        params.put("plot", "full");
        Call<Movie> call = RestClient.getClient().getMovieDetail(params);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();
                    if (movie.hasResponse()) {
                        List<Movie> list = new ArrayList<>();
                        list.add(movie);
                        Picasso.with(DetailActivity.this)
                                .load(movie.getPosterUrl())
                                .placeholder(R.mipmap.imdb)
                                .into(((ImageView) findViewById(R.id.detail_poster)));
                        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar)).setTitle(movie.getTitle());
                        recyclerView.setAdapter(new DetailAdapter(list));
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}
