package com.shumin.movie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.component.MovieCardLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final class ViewType {
        public static final int LOADING = 0;
        public static final int CONTENT = 1;
    }

    private List<Movie> content = new ArrayList<>();
    private boolean moreData;
    private int totalResults;


    public ResultAdapter (List<Movie> results, int totalResults) {
        content.addAll(results);
        this.totalResults = totalResults;
        moreData = content.size() < totalResults; // check if we need to do pagination
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewType.LOADING) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_loading, parent, false);
            return new LoadingViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_movie_card, parent, false);
            return new ResultViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder viewHolder = (LoadingViewHolder) holder;
            return;
        }
        ResultViewHolder resultViewHolder = (ResultViewHolder) holder;
        resultViewHolder.movieCardLayout.setContent(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size() + (moreData ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadingView(position)) {
            return ViewType.LOADING;
        } else {
            return ViewType.CONTENT;
        }
    }

    public void addMovies(List<Movie> movies) {
        content.addAll(movies);
        moreData = content.size() < totalResults;
        notifyDataSetChanged();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        MovieCardLayout movieCardLayout;

        public ResultViewHolder(View itemView) {
            super(itemView);
            movieCardLayout = (MovieCardLayout) itemView;
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loading_progress_bar);
        }
    }

    private boolean isLoadingView(int i) {
        int size = content.size();
        return size != 0 && size == i;
    }
}
