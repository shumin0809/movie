package com.shumin.movie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.component.MovieCardLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> content = new ArrayList<>();

    public ResultAdapter (List<Movie> results) {
        content.addAll(results);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_movie_card, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResultViewHolder resultViewHolder = (ResultViewHolder) holder;
        resultViewHolder.movieCardLayout.setContent(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        MovieCardLayout movieCardLayout;

        public ResultViewHolder(View itemView) {
            super(itemView);
            movieCardLayout = (MovieCardLayout) itemView;
        }

    }
}
