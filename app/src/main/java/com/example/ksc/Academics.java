package com.example.ksc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Academics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);

        LinearLayout plusTwoCard = findViewById(R.id.plusTwoCard);
        LinearLayout bachelorCard = findViewById(R.id.bachelorCard);
        LinearLayout masterCard = findViewById(R.id.masterCard);

        plusTwoCard.setOnClickListener(v -> {
            Intent intent = new Intent(Academics.this, PlusTwoActivity.class);
            startActivity(intent);
        });

        bachelorCard.setOnClickListener(v -> {
            Intent intent = new Intent(Academics.this, BachelorActivity.class);
            startActivity(intent);
        });

        masterCard.setOnClickListener(v -> {
            Intent intent = new Intent(Academics.this, MasterActivity.class);
            startActivity(intent);
        });
    }
}
