package com.a_team.studentlife.adapter.news;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a_team.studentlife.ProgressBars.ProgressService;
import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.LikeManipulationResponse;
import com.a_team.studentlife.UserInformation.User;
import com.a_team.studentlife.card_view_filling.NewsPost;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView participants;
    private TextView likes;
    private TextView userName;
    private TextView postText;
    private TextView postDate;
    private TextView postTime;
    private ImageView postedPhoto;
    private ImageView leaguePhoto;
    private ImageView likesImage;

    public NewsRecyclerViewHolder(View itemView) {
        super(itemView);
        participants = (TextView) itemView.findViewById(R.id.participantsStatistic);
        likes = (TextView) itemView.findViewById(R.id.likesStatistic);
        userName = (TextView) itemView.findViewById(R.id.userNewsNickname);
        postText = (TextView) itemView.findViewById(R.id.postedText);
        postedPhoto = (ImageView) itemView.findViewById(R.id.posted_photo_image_view);
        leaguePhoto = (ImageView) itemView.findViewById(R.id.news_profile_photo);
        postDate = (TextView) itemView.findViewById(R.id.date_of_event);
        postTime = (TextView) itemView.findViewById(R.id.time_of_event);
        likesImage = (ImageView) itemView.findViewById(R.id.likes_image_news);
    }

    @SuppressLint("SetTextI18n")
    public void bind(NewsPost newsPost) {
        participants.setText(newsPost.getParticipants().toString());
        likes.setText(newsPost.getLikes().toString());
        userName.setText(newsPost.getLeagueName());
        postText.setText(newsPost.getPostText());
        postTime.setText("Время: " + newsPost.getPostTime());
        postDate.setText("Дата: " + newsPost.getPostDate());
        if (newsPost.isLikedByMe()) {
            Picasso.get().load(R.drawable.ic_pos_likes_pressed).into(likesImage);
        } else {
            Picasso.get().load(R.drawable.ic_post_likes).into(likesImage);
        }
        setLikesImageListener(newsPost);
        Picasso.get().load(
                ApiUtils.getBaseUrl() +
                        "api/postedPhoto/viewimage?postedPhotoId=" +
                        newsPost.getPostIndex()).into(postedPhoto);
        Picasso.get().load(
                ApiUtils.getBaseUrl() +
                        "api/leaguePhoto/viewimage?leaguePhotoId=" +
                        newsPost.getLeagueIndex()).into(leaguePhoto);
    }

    @SuppressLint("SetTextI18n")
    private void setLikesImageListener(final NewsPost newsPost) {
        likesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newsPost.isLikedByMe()) {
                    Picasso.get().load(R.drawable.ic_post_likes).into(likesImage);
                    newsPost.setLikes(newsPost.getLikes() - 1);
                    likes.setText(newsPost.getLikes().toString());
                    decrementLikes(newsPost);
                    newsPost.setLikedByMe(false);
                } else {
                    Picasso.get().load(R.drawable.ic_pos_likes_pressed).into(likesImage);
                    newsPost.setLikes(newsPost.getLikes() + 1);
                    likes.setText(newsPost.getLikes().toString());
                    incrementLikes(newsPost);
                    newsPost.setLikedByMe(true);
                }
            }
        });
    }

    private void decrementLikes(NewsPost newsPost) {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.likeDecrement(User.getUserInstance().getId(), newsPost.getPostIndex()).enqueue(new Callback<LikeManipulationResponse>() {
            @Override
            public void onResponse(Call<LikeManipulationResponse> call, Response<LikeManipulationResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAnswer().equals("ok")) {
//                        Toast.makeText(
//                                itemView.getContext(),
//                                "Ваш голос учтен",
//                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LikeManipulationResponse> call, Throwable t) {
                ProgressService.showDialogMessage(itemView.getContext(), "Ошибка соединения",
                        "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                        2148, true);
            }
        });
    }

    private void incrementLikes(NewsPost newsPost) {
        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.likeIncrement(User.getUserInstance().getId(), newsPost.getPostIndex()).enqueue(new Callback<LikeManipulationResponse>() {
            @Override
            public void onResponse(Call<LikeManipulationResponse> call, Response<LikeManipulationResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAnswer().equals("ok")) {
//                        Toast.makeText(
//                                itemView.getContext(),
//                                "Ваш голос учтен",
//                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LikeManipulationResponse> call, Throwable t) {
                ProgressService.showDialogMessage(itemView.getContext(), "Ошибка соединения",
                        "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                        2148, true);
            }
        });
    }
}