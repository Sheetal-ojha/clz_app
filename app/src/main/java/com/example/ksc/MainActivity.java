package com.example.ksc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Initialization
        initTabLayout();
        initUIStyling();
        initViewFlipper();
        initNavigationCards();

        // Note: To fetch data from your Node.js API,
        // call a function here using Retrofit or Volley.
        // fetchDataFromNode();
    }

    private void initTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Add Tabs
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("About"));
        tabLayout.addTab(tabLayout.newTab().setText("Academics"));
        tabLayout.addTab(tabLayout.newTab().setText("Admission"));
        tabLayout.addTab(tabLayout.newTab().setText("Result"));
        tabLayout.addTab(tabLayout.newTab().setText("Notice"));
        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
        tabLayout.addTab(tabLayout.newTab().setText("Login"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Intent intent = null;
                switch (tab.getPosition()) {
                    case 1: intent = new Intent(MainActivity.this, AboutActivity.class); break;
                    case 2: intent = new Intent(MainActivity.this, Academics.class); break;
                    case 3: intent = new Intent(MainActivity.this, AdmissionActivity.class); break;
                    case 4: intent = new Intent(MainActivity.this, ResultActivity.class); break;
                    case 5: intent = new Intent(MainActivity.this, EventActivity.class); break;
                    case 7: intent = new Intent(MainActivity.this, LoginActivity.class); break;
                }
                if (intent != null) startActivity(intent);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void initUIStyling() {
        // Status bar setup
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            );
        }

        TextView headerText = findViewById(R.id.headerText);
        headerText.setText("Kathmandu Shiksha Campus");

        TextView contactInfo = findViewById(R.id.contactInfo);
        String contactText = "01-4311843 / 9851163618 / 9841292399 / WhatsApp / Viber: 9861830222\nkscrmc13@gmail.com";
        SpannableString contactSpannable = new SpannableString(contactText);
        contactSpannable.setSpan(new RelativeSizeSpan(0.85f), 0, contactText.length(), 0);
        contactInfo.setText(contactSpannable);

        TextView aboutDescription = findViewById(R.id.aboutDescription);
        String fullText = getString(R.string.about_college_text);
        String boldPart = "Kathmandu Shiksha Campus (KSC)";
        SpannableString spannable = new SpannableString(fullText);
        spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, boldPart.length(), 0);
        spannable.setSpan(new RelativeSizeSpan(1.1f), 0, boldPart.length(), 0);
        aboutDescription.setText(spannable);
    }

    private void initViewFlipper() {
        ViewFlipper flipper = findViewById(R.id.imageFlipper);
        flipper.setInAnimation(this, R.anim.slide_in_right);
        flipper.setOutAnimation(this, R.anim.slide_out_left);
        flipper.setFlipInterval(2000);
        flipper.setAutoStart(true);
    }

    private void initNavigationCards() {
        Button readMoreBtn = findViewById(R.id.readMoreBtn);
        readMoreBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AboutActivity.class)));

        CardView plusTwoCard = findViewById(R.id.plusTwoCard);
        CardView bachelorCard = findViewById(R.id.bachelorCard);
        CardView masterCard = findViewById(R.id.masterCard);

        plusTwoCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PlusTwoActivity.class)));
        bachelorCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BachelorActivity.class)));
        masterCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MasterActivity.class)));
    }

    // Helper methods for XML onClick attributes
    public void openResult(View view) { startActivity(new Intent(this, ResultActivity.class)); }
    public void openExam(View view) { startActivity(new Intent(this, ExamActivity.class)); }
    public void openEvent(View view) { startActivity(new Intent(this, EventActivity.class)); }
}