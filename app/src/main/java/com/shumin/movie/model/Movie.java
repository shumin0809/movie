package com.shumin.movie.model;

/**
 * Created by shumin on 4/3/16.
 */
public class Movie {

    private int id; // for database

    private String Poster;
    private String Title;
    private String Year;
    private String Type;
    private String imdbID;

    // movie detail
    private String Plot;
    private String Director;
    private boolean Response;
    private String Error = "";

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


    // get movie detail;
    public String getPlot() {
        return Plot;
    }

    public String getDirector() {
        return Director;
    }

    public boolean hasResponse() {
        return Response;
    }

    public String getError() {
        return Error;
    }


    // for database
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    public void setYear(String year) {
        this.Year = year;
    }
}
