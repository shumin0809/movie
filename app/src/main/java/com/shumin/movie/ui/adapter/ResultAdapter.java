package com.shumin.movie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result.Search> content = new ArrayList<>();

    public ResultAdapter (List<Result.Search> results) {
        content.addAll(results);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ResultViewHolder) holder).textView.setText(content.get(position).Title);
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ResultViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
        }

    }
}
