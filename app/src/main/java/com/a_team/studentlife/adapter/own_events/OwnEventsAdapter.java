package com.a_team.studentlife.adapter.own_events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.OwnEvent;

import java.util.ArrayList;
import java.util.List;

public class OwnEventsAdapter extends RecyclerView.Adapter<OwnEventsRecyclerViewHolder> {
    private ArrayList<OwnEvent> ownEvents = new ArrayList<>();

    public void addAllOwnEvents(List<OwnEvent> ownEvents) {
        if (this.ownEvents.size() != 0)
            this.ownEvents.clear();
        int position = getItemCount();
        this.ownEvents.addAll(ownEvents);
        notifyItemRangeChanged(position, this.ownEvents.size());
    }

    @NonNull
    @Override
    public OwnEventsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.own_event_card_item, parent, false);
        return new OwnEventsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnEventsRecyclerViewHolder holder, int position) {
        holder.bind(this.ownEvents.get(position));
    }

    @Override
    public int getItemCount() {
        return ownEvents.size();
    }
}
