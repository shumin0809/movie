package com.shumin.movie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.component.MovieFavoriteLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> content = new ArrayList<>();

    public FavoriteAdapter(List<Movie> results) {
        content.addAll(results);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_movie_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FavoriteViewHolder) holder).movieFavoriteLayout.setContent(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        MovieFavoriteLayout movieFavoriteLayout;
        public FavoriteViewHolder(View itemView) {
            super(itemView);
            movieFavoriteLayout = (MovieFavoriteLayout) itemView;
        }
    }


}
