package com.shumin.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.activity.MainActivity;
import com.shumin.movie.ui.adapter.FavoriteAdapter;

import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class FavoriteFragment extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.favorite_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Movie> movies = ((MainActivity) getActivity()).movieDbHelper.getAllMovies();

        recyclerView.setAdapter(new FavoriteAdapter(movies));

        return view;
    }
}
