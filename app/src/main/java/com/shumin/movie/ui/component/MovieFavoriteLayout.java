package com.shumin.movie.ui.component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shumin.movie.R;
import com.shumin.movie.constant.Constants;
import com.shumin.movie.event.Events;
import com.shumin.movie.model.Movie;
import com.shumin.movie.ui.activity.DetailActivity;
import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;

/**
 * Created by shumin on 4/3/16.
 */
public class MovieFavoriteLayout extends RelativeLayout {

    private Events.RemoveFromFavoriteEvent removeFromFavoriteEvent = new Events.RemoveFromFavoriteEvent();


    public MovieFavoriteLayout(Context context) {
        super(context);
    }

    public MovieFavoriteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContent(final Movie movie) {
        ((TextView) findViewById(R.id.title)).setText(movie.getTitle());
        Picasso.with(getContext()).load(movie.getPosterUrl())
                .placeholder(R.mipmap.imdb).into((ImageView) findViewById(R.id.poster));
        findViewById(R.id.delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage(R.string.confirm_message)
                        .setTitle(R.string.remove)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeFromFavoriteEvent.movie = movie;
                                EventBus.getDefault().post(removeFromFavoriteEvent);
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(Constants.MOVIE_ID, movie.getMovieId());
                getContext().startActivity(intent);
            }
        });

    }

}
