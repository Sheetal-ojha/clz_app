package com.example.ksc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdmissionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);

        Button btnApplyNow = findViewById(R.id.btnApplyNow);

        // Example: open online application page when clicked
        btnApplyNow.setOnClickListener(v -> {
            // Replace with your college application URL
            String url = "https://ksc.edu.np/admission";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Cannot open browser", Toast.LENGTH_SHORT).show();
            }
        });
    }
}