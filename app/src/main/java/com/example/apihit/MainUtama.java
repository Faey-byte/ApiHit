package com.example.apihit;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainUtama extends AppCompatActivity {
    private AppCompatButton btnPremier;
    private  AppCompatButton btnLiga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_utama);

        btnPremier= findViewById(R.id.btnPremiere);
        btnLiga= findViewById(R.id.btnLiga);

        btnPremier.setOnClickListener(v -> {
                    Intent intent= new Intent(MainUtama.this, MainActivity.class);
                    startActivity(intent);
                }
        );

        btnLiga.setOnClickListener(v->{
            Intent intent= new Intent(MainUtama.this, LaLigaActivity.class);
            startActivity(intent);
        }
        );

    }
}