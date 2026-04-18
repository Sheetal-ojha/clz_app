package com.example.ksc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        TextView tv = findViewById(R.id.tvDashboard);
        tv.setText("Welcome to Admin Dashboard!");
    }
}
