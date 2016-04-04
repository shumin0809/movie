package com.shumin.movie.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shumin.movie.R;
import com.shumin.movie.event.Events;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.activity.MainActivity;
import com.shumin.movie.ui.adapter.FavoriteAdapter;


import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by shumin on 4/3/16.
 */
public class FavoriteFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Movie> movies;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.favorite_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movies = ((MainActivity) getActivity()).movieDbHelper.getAllMovies();

        recyclerView.setAdapter(adapter = new FavoriteAdapter(movies));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(Events.AddToFavoriteEvent event) {
        ((FavoriteAdapter) adapter).addMovie(((MainActivity) getActivity()).movieDbHelper.getMovie(event.movieId));
    }

    public void onEvent(Events.RemoveFromFavoriteEvent event) {
        ((MainActivity) getActivity()).movieDbHelper.deleteMovie(event.movie);
        ((FavoriteAdapter) adapter).removeMovie(event.movie);
    }
}
