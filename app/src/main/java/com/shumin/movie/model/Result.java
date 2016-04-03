package com.shumin.movie.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class Result {

    // Generally, the variable name shouldn't start with capital.
    // Since I have Gson converter to convert the JSON response
    // to a Movie object, we need to keep the member variables
    // names the same as JSON field. GSON will make the conversion
    // from JSON to java model automatically.

    private Boolean Response;
    private int totalResults;
    private String Error = "";
    private List<Search> Search = new ArrayList<>();

    public boolean hasResponse() {
        return Response;
    }

    public int getSize() {
        return totalResults;
    }

    public String getError() {
        return Error;
    }

    public List<Search> getResults() {
        return this.Search;
    }

    public class Search {
        public String Poster;
        public String Title;
        public String Year;
        public String Type;
        public String imdbID;
    }
}
