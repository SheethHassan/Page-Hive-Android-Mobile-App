package com.example.bookfinder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookfinder.adapters.BookAdapter;
import com.example.bookfinder.models.Book;
import com.example.bookfinder.models.BookResponse;
import com.example.bookfinder.network.GoogleBooksApi;
import com.example.bookfinder.network.RetrofitClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edSearch;
    BookAdapter adapter;
    GoogleBooksApi api;
    List<Book> books = new ArrayList<>();
    Button btnSearch;
    RecyclerView rvBooks;
    Button btnMyLibrary;
    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edSearch = findViewById(R.id.edSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rvBooks = findViewById(R.id.rvBooks);
        btnMyLibrary = findViewById(R.id.btnMyLibrary);
        btnLogout = findViewById(R.id.btnLogout);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(this, books);
        rvBooks.setAdapter(adapter);
        api = RetrofitClient.getClient().create(GoogleBooksApi.class);
        btnSearch.setOnClickListener(v -> search());
        btnMyLibrary.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MyLibraryActivity.class));
        });
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }
    private void search() {
        String q = edSearch.getText().toString().trim();
        if(TextUtils.isEmpty(q)){ edSearch.setError("Enter a keyword");return;
        }
        api.searchBooks(q, 20).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if(response.isSuccessful() && response.body() != null && response.body().getItems() != null){
                    adapter.setBooks(response.body().getItems());
                }
                else{
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });





    }
}