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

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private List<ArticleResponse> places;

    public PlaceAdapter(List<ArticleResponse> places) {
        this.places = places;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final NetworkImageView image;
        public final TextView title;
        public final TextView category;
        public final TextView address;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title_textview);
            category = view.findViewById(R.id.category_textview);
            address = view.findViewById(R.id.address_textview);
        }
    }

    @NonNull
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new PlaceAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceAdapter.ViewHolder holder, int position) {
        ArticleResponse place = places.get(position);
        holder.image.setImageUrl(Constants.getServerUrl() + place.getPicUrl(), AppController.getInstance().getImageLoader());
        holder.title.setText(place.getTitle());
        holder.category.setText(place.getCategoryName());
        holder.address.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        if (places == null)
            return 0;
        else
            return places.size();
    }
}
