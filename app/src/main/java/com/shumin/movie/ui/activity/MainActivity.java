package com.shumin.movie.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Result;
import com.shumin.movie.rest.RestClient;
import com.shumin.movie.ui.adapter.ResultAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView resultBrif;
    private ResultAdapter adapter;

    private Result result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        resultBrif = (TextView) findViewById(R.id.result);

        Map<String, String> params = new LinkedHashMap<>();
        params.put("s", "ba");
        Call<Result> call = RestClient.getClient().searchMovie(params);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    result = response.body();
                    if (result.hasResponse()) {
                        resultBrif.setText("Total Results: " + result.getSize());
                        if (result.getSize() > 0) {
                            recyclerView.setAdapter(adapter = new ResultAdapter(result.getResults()));
                        }
                    } else {
                        resultBrif.setText(result.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
