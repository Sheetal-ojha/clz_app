package com.example.ksc;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.TextView;

public class BachelorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bachelor);

        TextView title = findViewById(R.id.bachelorTitle);
        title.setText("Bachelor Programs");

        CardView bcaCard = findViewById(R.id.bcaCard);
        bcaCard.setOnClickListener(v -> {
            Intent intent = new Intent(BachelorActivity.this, DetailBCAActivity.class);
            startActivity(intent);
        });

        // Add BBS and B.Ed cards similarly
        CardView bbsCard = findViewById(R.id.bbsCard);
        bbsCard.setOnClickListener(v -> {
            Intent intent = new Intent(BachelorActivity.this, DetailBCAActivity.class);
            startActivity(intent);
        });

        CardView bedCard = findViewById(R.id.bedCard);
        bedCard.setOnClickListener(v -> {
            Intent intent = new Intent(BachelorActivity.this, DetailBEdActivity.class);
            startActivity(intent);
        });
    }
}
