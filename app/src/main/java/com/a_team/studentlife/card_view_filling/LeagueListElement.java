package com.a_team.studentlife.card_view_filling;

import android.widget.Toast;

import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ListLeaguesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeagueListElement {
    private Integer leagueImageId;
    private String leagueName;

    public LeagueListElement(Integer leagueImageId, String leagueName) {
        this.leagueImageId = leagueImageId;
        this.leagueName = leagueName;
    }

    public Integer getLeagueImageId() {
        return leagueImageId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public static List<LeagueListElement> getLeagueListElements() {
        APIService mAPIService = ApiUtils.getAPIService();
        ArrayList<LeagueListElement> leagueListElements = new ArrayList<>();

        mAPIService.getListOfLeagues(0).enqueue(new Callback<ListLeaguesResponse>() {
            @Override
            public void onResponse(Call<ListLeaguesResponse> call, Response<ListLeaguesResponse> response) {
                if(response.isSuccessful()) {
                    //showResponse(response.body().getMessage());

                } else {

                }
            }

            @Override
            public void onFailure(Call<ListLeaguesResponse> call, Throwable t) {

            }
        });

        return leagueListElements;
    }

}
