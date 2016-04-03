package com.shumin.movie.model;

/**
 * Created by shumin on 4/3/16.
 */
public class Movie {

    // Generally, the variable name shouldn't start with capital.
    // Since I have Gson converter to convert the JSON response
    // to a Movie object, we need to keep the member variables
    // names the same as JSON field. GSON will make the conversion
    // from JSON to java model automatically.

    public String Title;
    public String Director;
    public String Year;
    public String Plot;
    public String Poster;

}
