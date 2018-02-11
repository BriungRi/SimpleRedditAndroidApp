package com.example.home.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 2/10/2018.
 */

public class RedditNewsAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<RedditChildrenResponse> redditChildrenResponses;

    public RedditNewsAdapter() {
        updateArticles(new ArrayList<RedditChildrenResponse>());
    }

    public void updateArticles(List<RedditChildrenResponse> redditChildrenResponses) {
        this.redditChildrenResponses = redditChildrenResponses;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View thisView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reddit_article, parent, false);
        return new ArticleViewHolder(thisView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.populate(redditChildrenResponses.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return redditChildrenResponses.size();
    }
}
