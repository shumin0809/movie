package com.shumin.movie.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.model.Movie;

/**
 * Created by shumin on 4/3/16.
 */
public class MovieDetailLayout extends LinearLayout {

    public MovieDetailLayout(Context context) {
        super(context);
    }

    public MovieDetailLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContent(Movie movie) {
        ((TextView) findViewById(R.id.summary)).setText(movie.getPlot());
        ((TextView) findViewById(R.id.director)).setText(movie.getDirector());
    }
}
