package com.a_team.studentlife.adapter.leagues_interface;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.LeagueListElement;
import com.a_team.studentlife.card_view_filling.NewsPost;

import java.util.ArrayList;
import java.util.List;

public class LeaguesInterfaceAdapter extends RecyclerView.Adapter<LeaguesInterfaceRecyclerViewHolder> {
    private ArrayList<NewsPost> postItems = new ArrayList<>();

    public void addAllLeagueNews(List<NewsPost> items) {
        if (this.postItems.size() != 0)
            this.postItems.clear();
        int position = getItemCount();
        this.postItems.addAll(items);
        notifyItemRangeChanged(position, this.postItems.size());
    }

    @Override
    public LeaguesInterfaceRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_item, parent, false);
        return new LeaguesInterfaceRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeaguesInterfaceRecyclerViewHolder holder, int position) {
        holder.bind(this.postItems.get(position));
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }
}
