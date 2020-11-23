package com.haami.haami.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.haami.haami.models.responses.BookResponse;

import com.haami.haami.Constants;
import com.haami.haami.R;
import com.haami.haami.app.AppController;

import java.util.List;

public class MagazineAdapter extends RecyclerView.Adapter<MagazineAdapter.ViewHolder> {
    private List<BookResponse> books;

    public MagazineAdapter(List<BookResponse> books) {
        this.books = books;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final NetworkImageView image;
        public final TextView title;
        public final TextView number_date;
        public final TextView description;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title_textview);
            number_date = view.findViewById(R.id.number_date_textview);
            description = view.findViewById(R.id.description_textview);
        }
    }

    @NonNull
    @Override
    public MagazineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_magazine, parent, false);
        return new MagazineAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MagazineAdapter.ViewHolder holder, int position) {
        BookResponse book = books.get(position);
        holder.image.setImageUrl(Constants.getServerUrl() + book.getImageUrl(), AppController.getInstance().getImageLoader());
        holder.title.setText(book.getBookName());
        holder.number_date.setText(String.format(book.getMagazineNumber() == null || book.getReleaseDatePersian() == null ? "%s %s" : "%s - %s", book.getMagazineNumber() == null ? "" : book.getMagazineNumber(), book.getReleaseDatePersian() == null ? "" : book.getReleaseDatePersian()));
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
