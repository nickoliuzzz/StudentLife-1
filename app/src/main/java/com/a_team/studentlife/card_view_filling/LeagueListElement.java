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
import com.a_team.studentlife.Server.ServerResponse.ListLeaguesResponse;
import com.a_team.studentlife.adapter.leagues.LeaguesAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeagueListElement {
    private Integer leagueIndex;
    private String leagueName;
    public static ArrayList<LeagueListElement> leagueListElements = new ArrayList<>();

    public LeagueListElement(Integer leagueIndex, String leagueName) {
        this.leagueIndex = leagueIndex;
        this.leagueName = leagueName;
    }

    public Integer getLeagueIndex() {
        return leagueIndex;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public static void getLeagueListElements(final Context context,
                                             final LeaguesAdapter leaguesAdapter,
                                             final RecyclerView recyclerView,
                                             final ProgressBar progressBarSpinner,
                                             int userId,
                                             final boolean shopFlag) {
        if (leagueListElements.size() != 0)
            leagueListElements.clear();

        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.getListOfLeagues(userId).enqueue(new Callback<ListLeaguesResponse>() {
            @Override
            public void onResponse(Call<ListLeaguesResponse> call, Response<ListLeaguesResponse> response) {
                if (response.isSuccessful()) {
                    ListLeaguesResponse listFromServer = response.body();
                    updateLeagueList(listFromServer, leagueListElements);
                    leaguesAdapter.addAllLeagues(leagueListElements, shopFlag);
                    recyclerView.setAdapter(leaguesAdapter);
                } else {
                    Toast.makeText(context, "Сервер вернул ошибку", Toast.LENGTH_SHORT).show();
                }
                progressBarSpinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ListLeaguesResponse> call, Throwable t) {
                //Toast.makeText(context, "Проверте соединение с интернетом", Toast.LENGTH_SHORT).show();
                //progressBarSpinner.setVisibility(View.VISIBLE);
                ProgressService.showDialogMessage(context, "Ошибка соединения",
                        "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                        2148, true);
            }
        });
    }

    private static void updateLeagueList(ListLeaguesResponse listFromServer,
                                         ArrayList<LeagueListElement> leagueListElements) {
        for (int i = 0; i < listFromServer.getIndexes().size(); i++) {
            leagueListElements.add(new LeagueListElement(listFromServer.getIndexes().get(i),
                    listFromServer.getNames().get(i)));
        }
    }

}