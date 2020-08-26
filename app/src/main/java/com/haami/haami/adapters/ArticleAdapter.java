package com.haami.haami.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

import com.haami.haami.Constants;
import com.haami.haami.R;
import com.haami.haami.app.AppController;
import com.haami.haami.models.responses.BookResponse;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<BookResponse> articles;

    public ArticleAdapter(List<BookResponse> articles) {
        this.articles = articles;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final NetworkImageView image;
        public final TextView title;
        public final TextView writer;
        public final TextView description;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title_textview);
            writer = view.findViewById(R.id.writer_textview);
            description = view.findViewById(R.id.description_textview);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookResponse article = articles.get(position);
        holder.image.setImageUrl(Constants.getServerUrl() + article.getImageUrl(), AppController.getInstance().getImageLoader());
        holder.writer.setText(article.getWriter());
        holder.title.setText(article.getBookName());
        holder.description.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        if (articles == null)
            return 0;
        else
            return articles.size();
    }
}
