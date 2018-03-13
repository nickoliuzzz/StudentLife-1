package com.a_team.studentlife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.NewsPost;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<NewsPost> postItems = new ArrayList<>();

    public void addAll(List<NewsPost> items) {
        int position = getItemCount();
        this.postItems.addAll(items);
        notifyItemRangeChanged(position, this.postItems.size());
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(this.postItems.get(position));
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }
}
