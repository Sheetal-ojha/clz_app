package com.example.ksc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Spinner spinnerClass;
    CheckBox checkboxTerms;
    Button btnCreateAccount, btnUploadPhoto;
    EditText etStudentId, etName, etEmail, etPhone, etPassword, etConfirmPassword;
    ImageView imgProfile;
    Uri imageUri;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.activity.EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        spinnerClass = findViewById(R.id.spinnerClass);
        checkboxTerms = findViewById(R.id.checkboxTerms);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnUploadPhoto = findViewById(R.id.btnUploadPhoto);
        imgProfile = findViewById(R.id.imgProfile);
        etStudentId = findViewById(R.id.etStudentId);
        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.etRegEmail);
        etPhone = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etRegPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnCreateAccount.setEnabled(false);
        checkboxTerms.setOnCheckedChangeListener((b, checked) ->
                btnCreateAccount.setEnabled(checked)
        );

        String[] classOptions = {"11", "12", "Bachelor (BCA)", "Bachelor (BBS)", "Bachelor (B.ED)", "Master"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, classOptions);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerClass.setAdapter(adapter);

        btnUploadPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 100);
        });

        btnCreateAccount.setOnClickListener(v -> {

            String studentId = etStudentId.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();
            String selectedClass = spinnerClass.getSelectedItem().toString();
            String imagePath = imageUri != null ? imageUri.toString() : "";

            if (studentId.isEmpty() || name.isEmpty() || email.isEmpty() ||
                    phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!studentId.matches("\\d{4,12}")) {
                etStudentId.setError("Enter valid Student ID");
                return;
            }

            if (!phone.matches("\\d{10,15}")) {
                etPhone.setError("Enter valid phone number");
                return;
            }

            if (!email.endsWith("@gmail.com") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Enter valid Gmail");
                return;
            }

            if (!isValidPassword(password)) {
                etPassword.setError("Weak password");
                return;
            }

            if (!password.equals(confirmPassword)) {
                etConfirmPassword.setError("Passwords do not match");
                return;
            }

            if (dbHelper.isEmailExists(email)) {
                etEmail.setError("Email already registered");
                return;
            }

            boolean inserted = dbHelper.insertUser(
                    studentId, name, email, phone, password, selectedClass, imagePath
            );

            if (inserted) {
                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(
                "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\\$%^&+=!]).{6,}$"
        );
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imgProfile.setImageURI(imageUri);
        }
    }
}
