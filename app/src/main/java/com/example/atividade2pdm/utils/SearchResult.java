package com.example.atividade2pdm.utils;

import com.example.atividade2pdm.models.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    @SerializedName("Search")
    private List<Movie> search;

    public List<Movie> getSearch() {
        return search;
    }
}
