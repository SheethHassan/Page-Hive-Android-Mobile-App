package com.example.bookfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookfinder.R;
import com.example.bookfinder.models.SavedBook;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SavedBookAdapter extends RecyclerView.Adapter<SavedBookAdapter.SavedVH> {

    private final Context context;
    private List<SavedBook> list;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final FirebaseAuth auth = FirebaseAuth.getInstance();

    public SavedBookAdapter(Context context, List<SavedBook> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<SavedBook> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SavedVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_saved_book, parent, false);
        return new SavedVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedVH holder, int position) {
        SavedBook book = list.get(position);
        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.edNotes.setText(book.getNotes());
        holder.spStatus.setSelection(getIndex(book.getStatus()));

        if (book.getThumb() != null && !book.getThumb().isEmpty()) {
            Glide.with(context).load(book.getThumb()).into(holder.ivCover);
        } else {
            holder.ivCover.setImageResource(R.drawable.ic_book_placeholder);
        }

        holder.btnUpdate.setOnClickListener(v -> {
            String userId = auth.getCurrentUser().getUid();
            DocumentReference bookRef = db.collection("users")
                    .document(userId).collection("saved_books").document(book.getBookId());

            bookRef.update(
                    "status", holder.spStatus.getSelectedItem().toString(),
                    "notes", holder.edNotes.getText().toString()
            ).addOnSuccessListener(unused -> {
                Toast.makeText(context, "Book updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(context, "Error updating book", Toast.LENGTH_SHORT).show();
            });
        });

        holder.btnRemove.setOnClickListener(v -> {
            String userId = auth.getCurrentUser().getUid();
            db.collection("users").document(userId)
                    .collection("saved_books").document(book.getBookId())
                    .delete()
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(context, "Book removed", Toast.LENGTH_SHORT).show();
                        // Check bounds to prevent crash
                        if (position >= 0 && position < list.size()) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, list.size());
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(context, "Error removing book", Toast.LENGTH_SHORT).show();
                    });
        });
    }

    private int getIndex(String status) {
        if (status == null) return 0;
        switch (status) {
            case "Reading": return 1;
            case "Wishlist": return 2;
            case "Dropped": return 3;
            default: return 0;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    // FIX IS HERE: The constructor was moved INSIDE the class
    static class SavedVH extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle, tvAuthor;
        EditText edNotes;
        Spinner spStatus;
        Button btnUpdate, btnRemove;

        public SavedVH(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            edNotes = itemView.findViewById(R.id.edNotes);
            spStatus = itemView.findViewById(R.id.spStatus);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
