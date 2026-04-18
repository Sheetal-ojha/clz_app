package com.example.ksc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    Button btnAddResult;
    TextView tvResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnAddResult = findViewById(R.id.btnAddResult);
        tvResultList = findViewById(R.id.tvResultList);

        // Get role from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String role = prefs.getString("role", "Student"); // default = Student

        // Show button only for teachers
        if(role.equals("Teacher")) {
            btnAddResult.setVisibility(View.VISIBLE);
        } else {
            btnAddResult.setVisibility(View.GONE);
        }

        // Button click
        btnAddResult.setOnClickListener(v -> {
            // Teacher adds a result (replace this with your actual add logic)
            Toast.makeText(this, "Add Result clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
