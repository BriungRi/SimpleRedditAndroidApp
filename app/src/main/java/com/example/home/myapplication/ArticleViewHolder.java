package com.example.home.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by home on 2/10/2018.
 */

class ArticleViewHolder extends RecyclerView.ViewHolder {
    private final View itemView;
    private final TextView title;
    private final ImageView thumbnail;

    ArticleViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.title = itemView.findViewById(R.id.articleTitle);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
    }

    public void populate(RedditNewsDataResponse redditNewsDataResponse) {
        this.title.setText(redditNewsDataResponse.getTitle());
        Picasso.with(itemView.getContext()).load(redditNewsDataResponse.getThumbnail()).into(thumbnail);
    }
}
