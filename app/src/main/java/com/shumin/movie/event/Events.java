package com.shumin.movie.event;

import com.shumin.movie.model.Movie;

/**
 * Created by shumin on 4/3/16.
 */
public class Events {

    public static class AddToFavoriteEvent {
        public String movieId;
    }

    public static class RemoveFromFavoriteEvent {
        public Movie movie;
    }

}
