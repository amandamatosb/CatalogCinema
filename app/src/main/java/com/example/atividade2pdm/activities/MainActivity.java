package com.example.atividade2pdm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade2pdm.models.Movie;
import com.example.atividade2pdm.services.MovieService;
import com.example.atividade2pdm.services.RetrofitClient;
import com.example.atividade2pdm.utils.MyAdapter;
import com.example.atividade2pdm.R;
import com.example.atividade2pdm.utils.SavePreferences;
import com.example.atividade2pdm.utils.SearchResult;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextInputEditText editText;
    private RecyclerView recyclerView;
    private ArrayList<Movie> list;
    private MovieService movieService;
    private SavePreferences savePreferences;
    private MyAdapter adapter;
    private final String API_KEY = "a1b4d424";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText =findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);

        this.list = new ArrayList<Movie>();
        adapter = new MyAdapter(this, list);
        savePreferences = new SavePreferences(MainActivity.this);
        movieService = RetrofitClient.getClient().create(MovieService.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                String movieID = list.get(position).getImdbID();

                intent.putExtra("movie_id", movieID);

                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Would you like to add this quote to your favorites?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        savePreferences.saveFavoriteMovie(list.get(position));
                        Toast.makeText(MainActivity.this, "Added to favorites!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create().show();
            }
        });

        button.setOnClickListener(v -> {
            String searchItem = editText.getText().toString();
            if (!searchItem.isEmpty()) {
                retrieveData(searchItem);
            } else {
                Toast.makeText(MainActivity.this, "Enter a title!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void retrieveData(String movieTitle) {
        Call<SearchResult> call = movieService.searchMovies(API_KEY, movieTitle);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getSearch() != null) {
                    list.clear();
                    list.addAll(response.body().getSearch());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}