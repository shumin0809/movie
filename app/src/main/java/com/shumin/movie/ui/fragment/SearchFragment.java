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

    private static final int MOVIES_PER_PAGE = 10;

    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private TextView searchResult;

    private Result result;

    private int page = 1;
    private LinearLayoutManager linearLayoutManager;

    private boolean isLoading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        searchResult = (TextView) view.findViewById(R.id.result);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    if (!isLoading) {
                        if (isLastItemVisible()) {
                            if (MOVIES_PER_PAGE * page < result.getSize()) {
                                search("tt", ++page);
                            }
                        }
                    }
                }
            }
        });

        search("tt", page);

        return view;
    }

    private void search(String search, int page) {
        isLoading = true;
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("s", search);
        params.put("page", page);
        Call<Result> call = RestClient.getClient().searchMovie(params);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                isLoading = false;
                if (response.isSuccessful()) {
                    result = response.body();
                    if (result.hasResponse()) {
                        if (result.getSize() > 0) {
                            if (result.getSize() == 1) {
                                searchResult.setText(getString(R.string.search_result));
                            } else {
                                searchResult.setText(getString(R.string.search_results, result.getSize()));
                            }
                            if (adapter == null) {
                                recyclerView.setAdapter(adapter = new ResultAdapter(result.getMovies(), result.getSize()));
                            } else {
                                adapter.addMovies(result.getMovies());
                            }
                        }
                    } else {
                        searchResult.setText(result.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                isLoading = false;
            }
        });
    }

    private boolean isLastItemVisible() {
        return linearLayoutManager.findLastVisibleItemPosition() >= linearLayoutManager.getItemCount() - 1;
    }
}
