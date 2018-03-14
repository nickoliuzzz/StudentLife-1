package com.a_team.studentlife.adapter.leagues_interface;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.NewsPost;

public class LeaguesInterfaceRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView participants;
    private TextView likes;
    private TextView leagueName;
    private TextView postText;
    private ImageView postedPhoto;
    private ImageView leaguePhoto;

    public LeaguesInterfaceRecyclerViewHolder(View itemView) {
        super(itemView);
        participants = (TextView) itemView.findViewById(R.id.participantsStatistic);
        likes = (TextView) itemView.findViewById(R.id.likesStatistic);
        leagueName = (TextView) itemView.findViewById(R.id.userNewsNickname);
        postText = (TextView) itemView.findViewById(R.id.postedText);
        postedPhoto = (ImageView) itemView.findViewById(R.id.posted_photo_image_view);
        leaguePhoto = (ImageView) itemView.findViewById(R.id.news_profile_photo);
    }

    @SuppressLint("SetTextI18n")
    public void bind(NewsPost newsPost) {
        participants.setText(newsPost.getParticipants().toString());
        likes.setText(newsPost.getLikes().toString());
        leagueName.setText(newsPost.getLeagueName());
        postText.setText(newsPost.getPostText());
        //postedPhoto.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), newsPost.getPostImageId()));
        //leaguePhoto.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), newsPost.getUserImageId()));
    }
}
