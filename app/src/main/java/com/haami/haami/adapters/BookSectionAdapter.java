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
import com.haami.haami.models.responses.BookSectionResponse;

import java.util.List;

public class BookSectionAdapter extends RecyclerView.Adapter<BookSectionAdapter.ViewHolder> {
    private List<BookSectionResponse> bookSections;

    public BookSectionAdapter(List<BookSectionResponse> bookSections) {
        this.bookSections = bookSections;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final NetworkImageView image;
        public final TextView title;
        public final ImageView audioImage;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title_textview);
            audioImage = view.findViewById(R.id.audio_image);
        }
    }

    @NonNull
    @Override
    public BookSectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_section, parent, false);
        return new BookSectionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookSectionAdapter.ViewHolder holder, int position) {
        BookSectionResponse bookSection = bookSections.get(position);
        holder.image.setImageUrl(Constants.getServerUrl() + bookSection.getImageUrl(), AppController.getInstance().getImageLoader());
        holder.title.setText(bookSection.getTitle());
        holder.audioImage.setVisibility(bookSection.getAudioUrl().isEmpty() ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        if (bookSections == null)
            return 0;
        else
            return bookSections.size();
    }
}
