package com.shumin.movie.ui.component;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.activity.DetailActivity;
import com.shumin.movie.ui.activity.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by shumin on 4/3/16.
 */
public class MovieCardLayout extends CardView {

    private Movie movie;

    public MovieCardLayout(Context context) {
        super(context);
    }

    public MovieCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContent(final Movie movie) {
        this.movie = movie;
        ImageView imageView = (ImageView) findViewById(R.id.poster);
        Picasso.with(getContext()).load(movie.getPosterUrl()).placeholder(R.mipmap.imdb).into(imageView);
        ((TextView) findViewById(R.id.title)).setText(movie.getTitle());
        ((TextView) findViewById(R.id.release_year)).setText(movie.getReleaseYear());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("imdbid", MovieCardLayout.this.movie.getMovieId());
                getContext().startActivity(intent);
            }
        });

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) getContext()).movieDbHelper.addMovie(movie);
                Log.d("onLongClick", "size = " + ((MainActivity) getContext()).movieDbHelper.getMoviesSize());
                return true;
            }
        });
    }

}
