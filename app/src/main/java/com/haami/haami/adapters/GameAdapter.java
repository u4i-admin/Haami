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

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private List<ArticleResponse> games;

    public GameAdapter(List<ArticleResponse> games) {
        this.games = games;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final NetworkImageView image;
        public final TextView title;
        public final TextView description;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title_textview);
            description = view.findViewById(R.id.description_textview);
        }
    }

    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameAdapter.ViewHolder holder, int position) {
        ArticleResponse place = games.get(position);
        holder.image.setImageUrl(Constants.getServerUrl() + place.getPicUrl(), AppController.getInstance().getImageLoader());
        holder.title.setText(place.getTitle());
        holder.description.setText(place.getBody());
    }

    @Override
    public int getItemCount() {
        if (games == null)
            return 0;
        else
            return games.size();
    }
}
