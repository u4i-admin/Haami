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
import com.haami.haami.models.responses.ArticleResponse;

import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    private final List<ArticleResponse> links;

    public LinkAdapter(List<ArticleResponse> links) {
        this.links = links;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final NetworkImageView image;
        public final TextView title;
        public final TextView category;
        public final TextView address;
        public final TextView description;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title_textview);
            category = view.findViewById(R.id.category_textview);
            address = view.findViewById(R.id.address_textview);
            description = view.findViewById(R.id.description_textview);
        }
    }

    @NonNull
    @Override
    public LinkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new LinkAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LinkAdapter.ViewHolder holder, int position) {
        ArticleResponse place = links.get(position);
        holder.image.setImageUrl(Constants.getServerUrl() + place.getPicUrl(), AppController.getInstance().getImageLoader());
        holder.title.setText(place.getTitle());
        holder.category.setText(place.getCategoryName());
        holder.address.setText(place.getBody());
        holder.description.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        if (links == null)
            return 0;
        else
            return links.size();
    }
}
