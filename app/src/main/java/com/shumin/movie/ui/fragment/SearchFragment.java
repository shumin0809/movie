package com.shumin.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by shumin on 4/3/16.
 */
public class SearchFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private TextView resultBrif;
    private ResultAdapter adapter;

    private Result result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        resultBrif = (TextView) view.findViewById(R.id.result);

        search();

        return view;
    }

    private void search() {
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
