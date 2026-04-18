package com.example.ksc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ksc.R;

public class teacherDashboard extends AppCompatActivity {

    TextView cardAttendance, cardResult, cardAssignment, cardRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        cardAttendance = findViewById(R.id.cardAttendance);
        cardResult = findViewById(R.id.cardResult);
        cardAssignment = findViewById(R.id.cardAssignment);
        cardRoutine = findViewById(R.id.cardRoutine);

        cardAttendance.setOnClickListener(v ->
                        Toast.makeText(this, "Attendance clicked", Toast.LENGTH_SHORT).show()
                // startActivity(new Intent(this, AttendanceActivity.class))
        );

        cardResult.setOnClickListener(v ->
                Toast.makeText(this, "Upload Result clicked", Toast.LENGTH_SHORT).show()
        );

        cardAssignment.setOnClickListener(v ->
                Toast.makeText(this, "Upload Assignment clicked", Toast.LENGTH_SHORT).show()
        );

        cardRoutine.setOnClickListener(v ->
                Toast.makeText(this, "Routine clicked", Toast.LENGTH_SHORT).show()
        );
    }
}
