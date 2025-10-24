package com.example.atividade2pdm.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.atividade2pdm.models.Movie;
import com.example.atividade2pdm.services.MovieService;
import com.example.atividade2pdm.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    private TextView textPlot;
    private TextView textTitle;
    private TextView textReleased;
    private TextView textRuntime;
    private ImageView imagePoster;
    private ImageView imageRating;
    private Button button_back;
    private ImageButton imageYoutube;

    private Retrofit retrofit;
    private final String API_KEY = "a1b4d424";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String url = "http://www.omdbapi.com";

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        textPlot = findViewById(R.id.textPlot);
        textReleased = findViewById(R.id.textReleased);
        textRuntime = findViewById(R.id.textRuntime);
        textTitle = findViewById(R.id.textTitle);
        imagePoster = findViewById(R.id.imagePoster);
        imageRating = findViewById(R.id.imageRating);
        button_back = findViewById(R.id.button_back);
        imageYoutube = findViewById(R.id.imageYoutube);
        String movieID = getIntent().getStringExtra("movie_id");

        retrieveDetailMovie(movieID);

        button_back.setOnClickListener( v -> {
            finish();
        });

    }

    public static int converter(String num) {
        float rating;

        rating = Float.parseFloat(num);

        double  newRating = rating/2.0;

        return (int) Math.round(newRating);
    }

    public void retrieveDetailMovie(String movieID) {
        MovieService movieService = retrofit.create(MovieService.class);
        Call<Movie> call = movieService. getMovieDetail(API_KEY, movieID);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movieDetail = response.body();
                    textTitle.setText(movieDetail.getTitle() + " (" + movieDetail.getYear() + ")");
                    textReleased.setText("Released: " + movieDetail.getReleased());
                    textRuntime.setText("Runtime: " + movieDetail.getRuntime());
                    textPlot.setText("Plot: " + movieDetail.getPlot());

                    Glide.with(DetailActivity.this)
                            .load(movieDetail.getPoster())
                            .placeholder(R.drawable.placeholder)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(16)))
                            .into(imagePoster);

                    int rating = converter(movieDetail.getImdbRating());

                    if(rating == 0) {
                        imageRating.setImageResource(R.drawable.img);
                    } else if(rating == 1) {
                        imageRating.setImageResource(R.drawable.img_1);
                    } else if(rating == 2) {
                        imageRating.setImageResource(R.drawable.img_2);
                    } else if(rating == 3) {
                        imageRating.setImageResource(R.drawable.img_3);
                    } else if(rating == 4) {
                        imageRating.setImageResource(R.drawable.img_4);
                    } else if(rating == 5) {
                        imageRating.setImageResource(R.drawable.img_5);
                    }

                    imageYoutube.setOnClickListener(v -> {
                        String urlString = getString(R.string.searchURL) + Uri.encode(movieDetail.getTitle() + " trailer ", "UTF-8");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                        startActivity(intent);
                    });

                } else {
                    Toast.makeText(DetailActivity.this, "No details found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}