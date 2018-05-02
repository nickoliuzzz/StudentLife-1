package com.a_team.studentlife.card_view_filling;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a_team.studentlife.ProgressBars.ProgressService;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ListAllUserNewsResponse;
import com.a_team.studentlife.Server.ServerResponse.ListLeagueNewsResponse;
import com.a_team.studentlife.UserInformation.User;
import com.a_team.studentlife.adapter.leagues_interface.LeaguesInterfaceAdapter;
import com.a_team.studentlife.adapter.news.NewsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPost {
    private Integer postIndex;
    private Integer participants;
    private Integer likes;
    private Integer postImageId;
    private Integer userImageId;
    private String leagueName;
    private String postText;
    private String postDate;
    private String postTime;
    private boolean isLikedByMe;
    public static ArrayList<NewsPost> newsPosts = new ArrayList<>();

    public NewsPost(Integer postIndex,
                    Integer participants,
                    Integer likes,
                    Integer postImageId,
                    Integer userImageId,
                    String leagueName,
                    String postText,
                    String postDate,
                    String postTime,
                    boolean isLikedByMe) {
        this.postIndex = postIndex;
        this.participants = participants;
        this.likes = likes;
        this.postImageId = postImageId;
        this.userImageId = userImageId;
        this.leagueName = leagueName;
        this.postText = postText;
        this.postDate = postDate;
        this.postTime = postTime;
        this.isLikedByMe = isLikedByMe;
    }

    public Integer getPostIndex() {
        return postIndex;
    }

    public boolean isLikedByMe() {
        return isLikedByMe;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPostTime() {
        return postTime;
    }

    public Integer getParticipants() {
        return participants;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getPostImageId() {
        return postImageId;
    }

    public Integer getUserImageId() {
        return userImageId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getPostText() {
        return postText;
    }

    public static void getPostItems(final Context context, final NewsAdapter newsAdapter,
                                    final LeaguesInterfaceAdapter leaguesInterfaceAdapter,
                                    final RecyclerView recyclerView, final ProgressBar progressBarSpinner,
                                    int id, boolean newsPlace, final LeagueListElement leagueListElement) {
        if (newsPosts.size() != 0)
            newsPosts.clear();

        APIService mAPIService = ApiUtils.getAPIService();
        if (newsPlace) {
            mAPIService.getListOfLeagueNews(User.getUserInstance().getId(), id).
                    enqueue(new Callback<ListLeagueNewsResponse>() {
                @Override
                public void onResponse(Call<ListLeagueNewsResponse> call, Response<ListLeagueNewsResponse> response) {
                    if (response.isSuccessful()) {
                        ListLeagueNewsResponse listLeagueNews = response.body();
                        updateLeagueNews(leagueListElement, listLeagueNews, newsPosts);
                        leaguesInterfaceAdapter.addAllLeagueNews(newsPosts);
                        recyclerView.setAdapter(leaguesInterfaceAdapter);
                        if (newsPosts.size() == 0)
                            Toast.makeText(context, "В данной лиге нет новостей", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Сервер вернул ошибку", Toast.LENGTH_SHORT).show();
                    }
                    progressBarSpinner.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ListLeagueNewsResponse> call, Throwable t) {
                    ProgressService.showDialogMessage(context, "Ошибка соединения",
                            "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                            2148, true);
                }
            });
        } else {
            mAPIService.getAllUserNews(id).enqueue(new Callback<ListAllUserNewsResponse>() {
                @Override
                public void onResponse(Call<ListAllUserNewsResponse> call, Response<ListAllUserNewsResponse> response) {
                    if (response.isSuccessful()) {
                        ListAllUserNewsResponse listAllUserNews = response.body();
                        updateAllUserNews(listAllUserNews, newsPosts);
                        newsAdapter.addAllNews(newsPosts);
                        recyclerView.setAdapter(newsAdapter);
                        if (newsPosts.size() == 0)
                            Toast.makeText(context, "Нет ближайших событий", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Сервер вернул ошибку", Toast.LENGTH_SHORT).show();
                    }
                    progressBarSpinner.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ListAllUserNewsResponse> call, Throwable t) {
                    ProgressService.showDialogMessage(context, "Ошибка соединения",
                            "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                            2148, true);
                }
            });
        }
    }

    public static void updateAllUserNews(ListAllUserNewsResponse listAllUserNews,
                                         ArrayList<NewsPost> newsPosts) {
        for (int i = 0; i < listAllUserNews.getIndex().size(); i++) {
            newsPosts.add(new NewsPost(
                    listAllUserNews.getIndex().get(i),
                    listAllUserNews.getPeopleNumber().get(i),
                    listAllUserNews.getLikeNumber().get(i),
                    1,
                    1,
                    listAllUserNews.getLeague().get(i),
                    listAllUserNews.getDescription().get(i),
                    listAllUserNews.getEventDate().get(i),
                    listAllUserNews.getEventTime().get(i),
                    listAllUserNews.getIsLikedByMe().get(i)));
        }
    }

    public static void updateLeagueNews(LeagueListElement leagueListElement,
                                        ListLeagueNewsResponse listLeagueNews,
                                        ArrayList<NewsPost> newsPosts) {
        for (int i = 0; i < listLeagueNews.getIndex().size(); i++) {
            newsPosts.add(new NewsPost(
                    listLeagueNews.getIndex().get(i),
                    listLeagueNews.getPeopleNumber().get(i),
                    listLeagueNews.getLikeNumber().get(i),
                    1,
                    1,
                    leagueListElement.getLeagueName(),
                    listLeagueNews.getDescription().get(i),
                    listLeagueNews.getEventDate().get(i),
                    listLeagueNews.getEventTime().get(i),
                    listLeagueNews.getIsLikedByMe().get(i)));
        }
    }
}
