package com.example.ksc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExamActivity extends AppCompatActivity {

    Button btnAddExam;
    TextView tvExamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        btnAddExam = findViewById(R.id.btnAddExam);
        tvExamList = findViewById(R.id.tvExamList);

        // Get role from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String role = prefs.getString("role", "Student"); // default = Student

        // Show button only for teachers
        if(role.equals("Teacher")) {
            btnAddExam.setVisibility(View.VISIBLE);
        } else {
            btnAddExam.setVisibility(View.GONE);
        }

        // Button click
        btnAddExam.setOnClickListener(v -> {
            // Teacher adds an exam (replace this with your actual add logic)
            Toast.makeText(this, "Add Exam clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
