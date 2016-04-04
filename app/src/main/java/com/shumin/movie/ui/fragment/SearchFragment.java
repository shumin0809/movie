package com.shumin.movie.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Result;
import com.shumin.movie.rest.RestClient;
import com.shumin.movie.ui.activity.MainActivity;
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


    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private TextView searchResult;
    private EditText searchData;
    private View searchContainer;
    private View topPanel;

    // get search result
    private Result result;
    private boolean isLoading;
    private int page = 1;
    private String currentData = "", previousData = "";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // initialize ui
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        searchResult = (TextView) view.findViewById(R.id.result);
        searchData = (EditText) view.findViewById(R.id.search_data);
        searchContainer = view.findViewById(R.id.search_container);
        topPanel = view.findViewById(R.id.topPanel);
        searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchData.hasFocus()) {
                    showKeyboard(searchData);
                }
            }
        });

        // search data
        view.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentData = searchData.getText().toString().trim();
                if (!previousData.equals(currentData)) {
                    showProgressDialog(R.string.fetch);
                    hideKeyboard(searchData);
                    search(currentData, page);
                }
            }
        });

        // pagination
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && !isLoading) {
                    if (isLastItemVisible()) {
                        if (MOVIES_PER_PAGE * page < result.getSize()) {
                            search(currentData, ++page);
                        }
                    }
                }
            }
        });

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
                dismissProgressDialog();
                if (response.isSuccessful()) {
                    result = response.body();
                    if (result.hasResponse()) {
                        topPanel.setVisibility(View.VISIBLE);
                        if (result.getSize() > 0) {
                            if (result.getSize() == 1) {
                                searchResult.setText(getString(R.string.search_result));
                            } else {
                                searchResult.setText(getString(R.string.search_results, result.getSize()));
                            }

                            // if it's not initialized or we start new search, reset the adapter.
                            if (adapter == null || !previousData.equals(currentData)) {
                                previousData = currentData;
                                recyclerView.setAdapter(adapter = new ResultAdapter(result.getMovies(), result.getSize()));
                            } else {
                                // pagination
                                adapter.addMovies(result.getMovies());
                            }
                        } else {
                            searchResult.setText(getString(R.string.search_results, result.getSize()));
                        }
                    } else {
                        ((MainActivity) getActivity()).showToast(result.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                isLoading = false;
                dismissProgressDialog();
            }
        });
    }

    // check if it reaches the bottom of the recycler view
    private boolean isLastItemVisible() {
        return linearLayoutManager.findLastVisibleItemPosition() >= linearLayoutManager.getItemCount() - 1;
    }
}
