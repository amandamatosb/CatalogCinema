package com.example.atividade2pdm;

import android.app.appsearch.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("/")
    Call<SearchResult> searchMovies(
            @Query("apiKey") String apiKey,
            @Query("s") String title);

    @GET("/")
    Call<Movie> getMovieDetail(
            @Query("apiKey") String apiKey,
            @Query("i") String imdbID);
}
