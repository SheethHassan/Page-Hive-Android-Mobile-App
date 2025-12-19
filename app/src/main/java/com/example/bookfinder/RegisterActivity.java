package com.example.bookfinder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Import Toast to give user feedback

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;


    EditText edName, edEmail, edPassword, edConfirmPassword;
    Button btnRegister;
    TextView tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoLogin = findViewById(R.id.tvGoLogin);

        btnRegister.setOnClickListener(v -> {
            registerUser();
        });

        tvGoLogin.setOnClickListener(v1 -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void registerUser() {
        String name = edName.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString();
        String confirm = edConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            edName.setError("Name is required");
            edName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            edEmail.setError("Email is required");
            edEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            edPassword.setError("Password is required");
            edPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirm)) {
            edConfirmPassword.setError("Confirm Password is required");
            edConfirmPassword.requestFocus();
            return;
        }
        if (!password.equals(confirm)) {
            edConfirmPassword.setError("Passwords do not match");
            edConfirmPassword.requestFocus();
            edPassword.setText("");
            edConfirmPassword.setText("");
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            //user data map
            String userId = task.getResult().getUser().getUid();
            Map<String, Object> user = new HashMap<>();
            user.put("name", name);
            user.put("email", email);
            //firestore map
            db.collection("users").document(userId).set(user).addOnSuccessListener(unused -> {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();

                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
        });
    }
}
