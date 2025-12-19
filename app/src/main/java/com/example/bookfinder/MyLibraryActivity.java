package com.example.bookfinder;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookfinder.adapters.SavedBookAdapter;
import com.example.bookfinder.models.SavedBook;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MyLibraryActivity extends AppCompatActivity {
    final List<SavedBook> list = new ArrayList<>();
    SavedBookAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_library);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView rvSavedBooks = findViewById(R.id.rvSavedBooks);
        rvSavedBooks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SavedBookAdapter(this, list);
        rvSavedBooks.setAdapter(adapter);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore.getInstance().
                collection("users").document(userId)
                .collection("saved_books")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Toast.makeText(MyLibraryActivity.this, "Error fetching saved books", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    list.clear();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        SavedBook book = doc.toObject(SavedBook.class);
                        if (book != null) list.add(book);
                    }
                    adapter.setList(list);
                });
                }



    }