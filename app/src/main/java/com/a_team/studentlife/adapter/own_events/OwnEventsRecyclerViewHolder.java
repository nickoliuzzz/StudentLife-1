package com.a_team.studentlife.adapter.own_events;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.OwnEvent;

public class OwnEventsRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView leagueName;
    private TextView eventName;
    private TextView eventDate;
    private TextView eventTime;
    private ImageView leagueImage;

    public OwnEventsRecyclerViewHolder(View itemView) {
        super(itemView);
        leagueImage = (ImageView) itemView.findViewById(R.id.leaguePhotoEvent);
        leagueName = (TextView) itemView.findViewById(R.id.leagueNameEvent);
        eventDate = (TextView) itemView.findViewById(R.id.date_of_own_event);
        eventTime = (TextView) itemView.findViewById(R.id.time_of_own_event);
        eventName = (TextView) itemView.findViewById(R.id.name_own_event);
    }

    @SuppressLint("SetTextI18n")
    public void bind(OwnEvent ownEvent) {
        leagueName.setText(ownEvent.getLeagueName());
        eventTime.setText(eventTime.getText() + " " + ownEvent.getPostTime());
        eventDate.setText(eventDate.getText() + " " + ownEvent.getPostDate());
        eventName.setText(eventName.getText() + " " + ownEvent.getEventName());
        // Еще фото установить
    }
}
