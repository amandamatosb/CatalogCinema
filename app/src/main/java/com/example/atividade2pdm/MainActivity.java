package com.example.atividade2pdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.annotations.SerializedName;

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
    private Retrofit retrofit;
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
        list = new ArrayList<Movie>();
        adapter = new MyAdapter(list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        String url = "http://www.omdbapi.com";

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//
//        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//            }
//        });

        button.setOnClickListener(v -> {
            String searchItem = editText.getText().toString();
            if (!searchItem.isEmpty()) {
                retrieveData(searchItem);
            } else {
                Toast.makeText(MainActivity.this, "Digite um filme!", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this, DetailActivity.class);
            String movieID = list.get(position).getImdbID();

            intent.putExtra("movie_id", movieID);

            startActivity(intent);
        });
    }


    public void retrieveData(String movieTitle) {
        MovieService movieService = retrofit.create(MovieService.class);
        Call<SearchResult> call = movieService.searchMovies(API_KEY, movieTitle);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getSearch() != null) {
                    list.clear();
                    list.addAll(response.body().getSearch());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Nenhum filme encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro de conexão" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}