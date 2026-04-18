package com.example.ksc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDashboardActivity extends AppCompatActivity {

    ImageView imgProfile;
    TextView tvName, tvEmail, tvStudentId, tvPhone, tvClass;
    LinearLayout resultCard, examCard, eventCard;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Check login status
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // User not logged in, redirect to LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // Close this activity
            return;
        }

        setContentView(R.layout.activity_student_dashboard);

        // Student info views
        imgProfile = findViewById(R.id.imgProfile);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvStudentId = findViewById(R.id.tvStudentId);
        tvPhone = findViewById(R.id.tvPhone);
        tvClass = findViewById(R.id.tvClass);

        // Result / Exam / Event cards
        resultCard = findViewById(R.id.resultBox);
        examCard = findViewById(R.id.examBox);
        eventCard = findViewById(R.id.eventBox);

        dbHelper = new DatabaseHelper(this);

        // Load student data from intent
        String email = getIntent().getStringExtra("email");
        if (email != null) {
            loadStudentData(email);
        } else {
            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
        }

        // Set click listeners for Result / Exam / Event
        resultCard.setOnClickListener(v -> openResult());
        examCard.setOnClickListener(v -> openExam());
        eventCard.setOnClickListener(v -> openEvent());
    }


    private void loadStudentData(String email) {
        Cursor cursor = dbHelper.getUserByEmail(email);

        if (cursor != null && cursor.moveToFirst()) {

            tvStudentId.setText("ID: " +
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_STUDENT_ID)));

            tvName.setText(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NAME)));

            tvEmail.setText(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_EMAIL)));

            tvPhone.setText("Phone: " +
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PHONE)));

            tvClass.setText("Class: " +
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CLASS)));

            String imageUri = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COL_IMAGE_URI));

            if (imageUri != null && !imageUri.isEmpty()) {
                imgProfile.setImageURI(Uri.parse(imageUri));
            }

            cursor.close();
        }
    }

    // Open Result Activity
    private void openResult() {
        startActivity(new Intent(this, ResultActivity.class));
    }

    // Open Exam Activity
    private void openExam() {
        startActivity(new Intent(this, ExamActivity.class));
    }

    // Open Event Activity
    private void openEvent() {
        startActivity(new Intent(this, EventActivity.class));
    }

    public void openFee(View view) {
        startActivity(new Intent(this, FeeActivity.class));
    }

    public void openHomework(View view) {
        startActivity(new Intent(this, HomeworkActivity.class));
    }

    public void openLms(View view) {
        startActivity(new Intent(this, LmsActivity.class));
    }

    public void openParentReport(View view) {
        startActivity(new Intent(this, ParentReportActivity.class));
    }

}
