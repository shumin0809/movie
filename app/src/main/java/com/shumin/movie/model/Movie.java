package com.shumin.movie.model;

/**
 * Created by shumin on 4/3/16.
 */
public class Movie {
    private String Poster;
    private String Title;
    private String Year;
    private String Type;
    private String imdbID;

    public String getPosterUrl() {
        return Poster;
    }

    public String getTitle() {
        return Title;
    }

    public String getReleaseYear() {
        return Year;
    }

    public String getType() {
        return Type;
    }

    public String getMovieId() {
        return imdbID;
    }
}
