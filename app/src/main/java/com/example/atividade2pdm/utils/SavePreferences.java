package com.example.atividade2pdm.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.atividade2pdm.models.Movie;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SavePreferences {
    private static final String PREFS_NAME = "favorites";
    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    public SavePreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveFavoriteMovie(Movie movie) {
        String json = gson.toJson(movie);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(movie.getImdbID()), json);
        editor.apply();
    }

    public Movie getFavoriteMovie(String movieId) {
        String json = sharedPreferences.getString(String.valueOf(movieId), null);
        if (json == null) return null;
        return gson.fromJson(json, Movie.class);
    }

    public void removeFavoriteMovie(String movieId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(movieId));
        editor.apply();
    }

    public boolean isFavorite(String movieId) {
        return sharedPreferences.contains(String.valueOf(movieId));
    }

    public void cleanFavorites() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (String key : sharedPreferences.getAll().keySet()) {
            String json = sharedPreferences.getString(key, null);
            if(json == null) continue;
            Movie movie = gson.fromJson(json, Movie.class);
            movies.add(movie);
        }
        return movies;
    }
}

