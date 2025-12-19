package com.example.bookfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookfinder.BookDetailActivity;
import com.example.bookfinder.R;
import com.example.bookfinder.models.Book;
import com.example.bookfinder.models.ImageLinks;
import com.example.bookfinder.models.VolumeInfo;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final Context context;
    private List<Book> books;

    public BookAdapter(Context context, List<Book> list) {
        this.context = context;
        this.books = list;

    }
    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();

}
@NonNull
@Override
public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book b = books.get(position);
        if (b == null) return;

        VolumeInfo volumeInfo = b.getVolumeInfo();
        String thumbnailUrl;
        String authors;
        if (volumeInfo != null) {
            holder.tvTitle.setText(volumeInfo.getTitle());
            // Handle author
            if (volumeInfo.getAuthors() != null && !volumeInfo.getAuthors().isEmpty()) {
                authors = android.text.TextUtils.join(", ", volumeInfo.getAuthors());
            } else {
                authors = "Author not available";
            }
            holder.tvAuthor.setText(authors);
            // Handle thumbnail
            ImageLinks imageLinks = volumeInfo.getImageLinks();
            if (imageLinks != null && imageLinks.getThumbnail() != null) {
                // Correct the URL replacement
                thumbnailUrl = imageLinks.getThumbnail().replace("http://", "https://");
            } else {
                thumbnailUrl = null;
            }
            // Load thumbnail using Glide
            Glide.with(context)
                    .load(thumbnailUrl)
                    .placeholder(R.drawable.ic_book_placeholder)
                    .error(R.drawable.ic_book_placeholder)
                    .into(holder.ivCover);

            // Handle click event
            holder.itemView.setOnClickListener(v -> {
                Intent i = new Intent(context, BookDetailActivity.class);


                i.putExtra("book_author", authors);
                i.putExtra("book_id", b.getId());
                i.putExtra("book_title", volumeInfo.getTitle());
                i.putExtra("book_description", volumeInfo.getDescription());


                if (thumbnailUrl != null) {
                    i.putExtra("THUMB", thumbnailUrl);
                }

                context.startActivity(i);
            });
        } else {
            thumbnailUrl = null;
            authors = "Author not available";
        }
    }

        @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }
    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor;
        ImageView ivCover;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            ivCover = itemView.findViewById(R.id.ivCover);
        }
    }
}



