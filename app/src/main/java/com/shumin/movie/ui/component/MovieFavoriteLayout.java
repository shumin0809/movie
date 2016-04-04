package com.shumin.movie.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by shumin on 4/3/16.
 */
public class MovieFavoriteLayout extends RelativeLayout {

    public MovieFavoriteLayout(Context context) {
        super(context);
    }

    public MovieFavoriteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContent(Movie movie) {
        ((TextView) findViewById(R.id.title)).setText(movie.getTitle());
        ((TextView) findViewById(R.id.year)).setText(movie.getReleaseYear());
        Picasso.with(getContext()).load(movie.getPosterUrl())
                .placeholder(R.mipmap.imdb).into((ImageView) findViewById(R.id.poster));
    }

}
