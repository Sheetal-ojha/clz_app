package com.example.ksc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText etEmail, etNewPass, etConfirmPass;
    Button btnReset;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        etEmail = findViewById(R.id.etEmail);
        etNewPass = findViewById(R.id.etNewPass);
        etConfirmPass = findViewById(R.id.etConfirmPass);
        btnReset = findViewById(R.id.btnReset);

        // Initialize database
        dbHelper = new DatabaseHelper(this);

        btnReset.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String newPass = etNewPass.getText().toString().trim();
            String confirmPass = etConfirmPass.getText().toString().trim();

            // 1️⃣ Empty check
            if (email.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            // 2️⃣ Check if email exists
            if (!dbHelper.isEmailExists(email)) {
                Toast.makeText(this, "Email not registered", Toast.LENGTH_SHORT).show();
                return;
            }

            // 3️⃣ Password match
            if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // 4️⃣ Update password
            boolean updated = dbHelper.updatePassword(email, newPass);

            if (updated) {
                Toast.makeText(this, "Password Updated Successfully!", Toast.LENGTH_SHORT).show();
                finish(); // back to Login
            } else {
                Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
