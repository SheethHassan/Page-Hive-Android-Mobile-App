package com.example.bookfinder;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent; // Import for Intent
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper; // Import for Looper if targeting newer Android versions

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth; // Import for FirebaseAuth

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Check if a user is currently logged in with Firebase
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                // If logged in, go to the MainActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                // If not logged in, go to the LoginActivity
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }, 2000);
    }
}
