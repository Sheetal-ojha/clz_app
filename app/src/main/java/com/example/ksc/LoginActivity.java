package com.example.ksc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etEmail, etPassword;
    Button btnLogin, btnRegister, btnForgotPassword;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);

        dbHelper = new DatabaseHelper(this);

        // ✅ LOGIN BUTTON
        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this , "Please enter email and password" , Toast.LENGTH_SHORT).show();
                return;
            }

            // --------- Start Role-Based Backend Login using Volley ---------
            String url = "http://192.168.227.138:3000/api/login";

            JSONObject loginData = new JSONObject();
            try {
                loginData.put("email" , email);
                loginData.put("password" , password);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("LOGIN_ERROR", "JSON creation failed: " + e.getMessage());
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST ,
                    url ,
                    loginData ,
                    response -> {
                        try {
                            if (response.getString("status").equals("success")) {
                                String role = response.getString("role");
                                String name = response.getString("name");
                                int id = response.getInt("id");

                                SharedPreferences prefs = getSharedPreferences("UserPrefs" , MODE_PRIVATE);
                                prefs.edit()
                                        .putBoolean("isLoggedIn" , true)
                                        .putString("email" , email)
                                        .putString("role" , role)
                                        .putInt("id" , id)
                                        .apply();

                                switch (role) {
                                    case "student":
                                        startActivity(new Intent(LoginActivity.this , StudentDashboardActivity.class)
                                                .putExtra("name" , name).putExtra("id" , id));
                                        finish();
                                        break;
                                    case "teacher":
                                        startActivity(new Intent(LoginActivity.this , teacherDashboard.class)
                                                .putExtra("name" , name).putExtra("id" , id));
                                        finish();
                                        break;
                                    case "admin":
                                        startActivity(new Intent(LoginActivity.this , AdminDashboard.class)
                                                .putExtra("name" , name).putExtra("id" , id));
                                        finish();
                                        break;
                                }
                            } else {
                                Toast.makeText(LoginActivity.this , response.getString("message") , Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this , "JSON Parsing Error" , Toast.LENGTH_SHORT).show();
                        }
                    } ,
                    error -> {
                        if (error.networkResponse != null) {
                            Toast.makeText(LoginActivity.this ,
                                    "Error Code: " + error.networkResponse.statusCode ,
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this ,
                                    "Error: " + error.toString() ,
                                    Toast.LENGTH_LONG).show();
                        }
                        error.printStackTrace();
                    }
            );

// Optional: set retry policy
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000 ,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES ,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));

            Volley.newRequestQueue(this).add(jsonObjectRequest);
        });

        // REGISTER BUTTON
        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );

        // FORGOT PASSWORD BUTTON
        btnForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class))
        );
    }
}
