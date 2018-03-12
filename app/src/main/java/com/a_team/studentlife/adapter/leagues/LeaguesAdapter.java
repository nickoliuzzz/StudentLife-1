package com.a_team.studentlife.adapter.leagues;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.LeagueListElement;

import java.util.ArrayList;
import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesRecyclerViewHolder>{
    private ArrayList<LeagueListElement> leagueListElements = new ArrayList<>();

    public void addAllLeagues(List<LeagueListElement> items) {
        int position = getItemCount();
        this.leagueListElements.addAll(items);
        notifyItemRangeChanged(position, this.leagueListElements.size());
    }

    @Override
    public LeaguesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leagues_card_item, parent, false);
        return new LeaguesRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeaguesRecyclerViewHolder holder, int position) {
        holder.bind(this.leagueListElements.get(position));
    }

    @Override
    public int getItemCount() {
        return leagueListElements.size();
    }
}
