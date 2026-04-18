package com.example.ksc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlusTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_two); // ✅ Make sure this matches your layout name

        // Set the title text
        TextView title = findViewById(R.id.plusTwoTitle);
        title.setText("+2 Programs");

        // ✅ Find the Science "Apply Now" button
        Button scienceApplyBtn = findViewById(R.id.scienceApplyBtn);

        // ✅ Open ScienceActivity when clicked
        scienceApplyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PlusTwoActivity.this, ScienceActivity.class);
            startActivity(intent);
        });
    }
}
