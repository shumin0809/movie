package com.shumin.movie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.component.MovieDetailLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> content = new ArrayList<>();

    public DetailAdapter(List<Movie> movies) {
        content.addAll(movies);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_detail, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DetailViewHolder) holder).movieDetailLayout.setContent(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder {
        MovieDetailLayout movieDetailLayout;

        public DetailViewHolder(View itemView) {
            super(itemView);
            movieDetailLayout = (MovieDetailLayout) itemView;
        }

    }
}
