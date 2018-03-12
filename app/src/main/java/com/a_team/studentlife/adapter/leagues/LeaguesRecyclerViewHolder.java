package com.a_team.studentlife.adapter.leagues;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.LeagueListElement;

class LeaguesRecyclerViewHolder extends RecyclerView.ViewHolder{
    private TextView leagueNameText;
    private ImageView leaguePhoto;

    public LeaguesRecyclerViewHolder(View itemView) {
        super(itemView);
        this.leagueNameText = (TextView) itemView.findViewById(R.id.leagueName);
        this.leaguePhoto = (ImageView) itemView.findViewById(R.id.leaguePhoto);
    }

    public void bind(LeagueListElement leagueListElement) {
        leagueNameText.setText(leagueListElement.getLeagueName());
        //leaguePhoto.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), leagueListElement.getLeagueImageId()));
    }
}
