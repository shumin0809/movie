package com.shumin.movie.ui.fragment;

import android.os.Bundle;
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
    private ResultAdapter adapter;
    private TextView searchResult;

    private Result result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchResult = (TextView) view.findViewById(R.id.result);

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
                        if (result.getSize() > 0) {
                            if (result.getSize() == 1) {
                                searchResult.setText(getString(R.string.search_result));
                            } else {
                                searchResult.setText(getString(R.string.search_results, result.getSize()));
                            }

                            recyclerView.setAdapter(adapter = new ResultAdapter(result.getMovies()));
                        }
                    } else {
                        searchResult.setText(result.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
