package com.example.ksc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ScienceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science); // layout for science page
    }

    // Opens full image with zoom option
    public void openFullImage(View view) {
        int imageRes = 0;

        // Match the IDs exactly from your XML

        if (imageRes != 0) {
            Intent intent = new Intent(this, FullImageActivity.class);
            intent.putExtra("imageRes", imageRes);
            startActivity(intent);
        }
    }
}
