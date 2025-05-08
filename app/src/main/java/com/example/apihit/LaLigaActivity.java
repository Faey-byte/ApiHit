package com.example.apihit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaLigaActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";
    private static final String TAG = "MainActivity";

    private RecyclerView rvListSport;
    private ProgressBar pbLoading;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListSport = findViewById(R.id.rvListSport);
        pbLoading= findViewById(R.id.pbLoading);
        pbLoading.setVisibility(View.VISIBLE);
        rvListSport.setLayoutManager(new LinearLayoutManager(this));

        // Setup Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SportsApi api = retrofit.create(SportsApi.class);

        Call<TeamResponse> call = api.getTeams("Spanish La Liga");
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pbLoading.setVisibility(View.GONE);
                    rvListSport.setVisibility(View.VISIBLE);
                    List<Team> teams = response.body().getTeams();

                    // 👉 Set adapter setelah data ready
                    TeamAdapter adapter = new TeamAdapter(LaLigaActivity.this, teams);
                    rvListSport.setAdapter(adapter);
                } else {
                    Log.e(TAG, "Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e(TAG, "API call failed: " + t.getMessage());
            }
        });
    }
}