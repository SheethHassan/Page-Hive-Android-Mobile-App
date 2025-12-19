package com.example.bookfinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookDetailActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        ImageView ivCover = findViewById(R.id.ivCover);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthor = findViewById(R.id.tvAuthor);
        TextView tvDesc = findViewById(R.id.tvDesc);
        Button btnAddToLibrary = findViewById(R.id.btnAddToLibrary);

        String bookId = getIntent().getStringExtra("bookId");
        String bookTitle = getIntent().getStringExtra("bookTitle");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        String bookDesc = getIntent().getStringExtra("bookDesc");
        String thumb = getIntent().getStringExtra("thumb");

        tvTitle.setText(bookTitle);
        tvAuthor.setText(bookAuthor);
        tvDesc.setText(bookDesc != null? bookDesc : "No description available");

        if (thumb != null && !thumb.isEmpty()) {
            Glide.with(this).load(thumb).into(ivCover);
        }
        else{
            Glide.with(this).load(R.drawable.ic_book_placeholder).into(ivCover);

        }
        btnAddToLibrary.setOnClickListener(v -> {
            String userId = auth.getCurrentUser().getUid();
            Map<String, Object> bookData = new HashMap<>();
            bookData.put("bookId", bookId);
            bookData.put("title", bookTitle);
            bookData.put("author", bookAuthor);
            bookData.put("thumb", thumb);
            bookData.put("status", "Wishlist");
            bookData.put("notes", "");

            db.collection("users").document(userId)
                    .collection("saved_books").document(bookId)
                    .set(bookData)
                    .addOnSuccessListener(unused ->
                    Toast.makeText(BookDetailActivity.this, "Book added to library", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> {
                        Toast.makeText(BookDetailActivity.this, "Error adding book to library", Toast.LENGTH_SHORT).show();
                    });
                });


    }
}