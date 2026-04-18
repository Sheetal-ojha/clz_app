package com.example.ksc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.ScaleGestureDetector;
import android.view.MotionEvent;

public class FullImageActivity extends AppCompatActivity {
    ImageView fullImage;
    ScaleGestureDetector scaleGestureDetector;
    float scaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        fullImage = findViewById(R.id.fullImageView);

        int imageRes = getIntent().getIntExtra("imageRes", 0);
        if (imageRes != 0) {
            fullImage.setImageResource(imageRes);
        }

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(1.0f, Math.min(scaleFactor, 5.0f));
            fullImage.setScaleX(scaleFactor);
            fullImage.setScaleY(scaleFactor);
            return true;
        }
    }
}
