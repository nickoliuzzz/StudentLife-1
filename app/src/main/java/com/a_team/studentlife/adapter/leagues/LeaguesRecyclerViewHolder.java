package com.a_team.studentlife.adapter.leagues;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.adapter.Shop.LeagueShopProductsActivity;
import com.a_team.studentlife.adapter.leagues_interface.LeagueActivity;
import com.a_team.studentlife.card_view_filling.LeagueListElement;

class LeaguesRecyclerViewHolder extends RecyclerView.ViewHolder{
    private TextView leagueNameText;
    private ImageView leaguePhoto;
    private LinearLayout leagueItem;

    public LeaguesRecyclerViewHolder(View itemView) {
        super(itemView);
        this.leagueNameText = (TextView) itemView.findViewById(R.id.leagueName);
        this.leaguePhoto = (ImageView) itemView.findViewById(R.id.leaguePhoto);
        this.leagueItem = (LinearLayout) itemView.findViewById(R.id.leagueItem);
    }

    private void setLeagueItemListener(LinearLayout leagueItem,
                                       final LeagueListElement leagueListElement,
                                       final boolean shopFlag) {
            leagueItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent leagueIntent;
                    if (shopFlag) {
                        leagueIntent = new Intent(itemView.getContext(), LeagueShopProductsActivity.class);
                    } else {
                        leagueIntent = new Intent(itemView.getContext(), LeagueActivity.class);
                    }
                    leagueIntent.putExtra("leagueIndex", leagueListElement.getLeagueIndex());
                    leagueIntent.putExtra("leagueName", leagueListElement.getLeagueName());
                    itemView.getContext().startActivity(leagueIntent);
                }
            });
    }

    public void bind(LeagueListElement leagueListElement, boolean shopFlag) {
        leagueNameText.setText(leagueListElement.getLeagueName());
        setLeagueItemListener(leagueItem, leagueListElement, shopFlag);
        //leaguePhoto.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), leagueListElement.getLeagueImageId()));
    }
}