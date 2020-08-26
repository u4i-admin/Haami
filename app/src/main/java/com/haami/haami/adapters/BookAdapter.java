package com.haami.haami.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

import com.haami.haami.Constants;
import com.haami.haami.R;
import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.BookResponse;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<BookResponse> books;

    public BookAdapter(List<BookResponse> books) {
        this.books = books;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final NetworkImageView image;
        public final ImageView audio_image;
        public final TextView name;
        public final TextView page_count;
        public final TextView description;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            audio_image = view.findViewById(R.id.audio_image);
            name = view.findViewById(R.id.name_textview);
            page_count = view.findViewById(R.id.page_count_textview);
            description = view.findViewById(R.id.description_textview);
        }
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        BookResponse book = books.get(position);
        holder.image.setImageUrl(Constants.getServerUrl() + book.getImageUrl(), AppController.getInstance().getImageLoader());
        holder.audio_image.setVisibility(book.getAudio() ? View.VISIBLE : View.GONE);
        holder.name.setText(book.getBookName());
        holder.page_count.setText(book.getSectionCount() + " صفحه");
        holder.description.setText(book.getDescription());
    }

    @Override
    public int getItemCount() {
        if (books == null)
            return 0;
        else
            return books.size();
    }
}
