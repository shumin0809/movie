package com.shumin.movie.ui.component;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
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

    public void setContent(Movie movie) {
        this.movie = movie;
        ImageView imageView = (ImageView) findViewById(R.id.poster);
        Picasso.with(getContext()).load(movie.getPosterUrl()).placeholder(R.mipmap.imdb).into(imageView);
        ((TextView) findViewById(R.id.title)).setText(movie.getTitle());
        ((TextView) findViewById(R.id.release_year)).setText(movie.getReleaseYear());
    }

}
