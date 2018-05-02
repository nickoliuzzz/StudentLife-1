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
import com.a_team.studentlife.Server.ServerResponse.OwnEventsResponse;
import com.a_team.studentlife.UserInformation.User;
import com.a_team.studentlife.adapter.own_events.OwnEventsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnEvent {
    private Integer eventImageId;
    private int eventId;
    private String leagueName;
    private String postDate;
    private String postTime;
    private String eventName;
    public static ArrayList<OwnEvent> ownEvents = new ArrayList<>();

    public OwnEvent(
            Integer eventImageId,
            int eventId,
            String leagueName,
            String postDate,
            String postTime,
            String eventName) {
        this.eventImageId = eventImageId;
        this.eventId = eventId;
        this.leagueName = leagueName;
        this.postDate = postDate;
        this.postTime = postTime;
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public Integer getEventImageId() {
        return eventImageId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPostTime() {
        return postTime;
    }

    public static void getEventItems(
            final Context context,
            final OwnEventsAdapter ownEventsAdapter,
            final RecyclerView recyclerView,
            final ProgressBar progressBarSpinner) {
        if (ownEvents.size() != 0)
            ownEvents.clear();

        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.getOwnEvents(User.getUserInstance().getId()).enqueue(new Callback<OwnEventsResponse>() {
            @Override
            public void onResponse(Call<OwnEventsResponse> call, Response<OwnEventsResponse> response) {
                if (response.isSuccessful()) {
                    OwnEventsResponse listFromServer = response.body();
                    updateListOfOwnEvents(listFromServer, ownEvents);
                    ownEventsAdapter.addAllOwnEvents(ownEvents);
                    recyclerView.setAdapter(ownEventsAdapter);
                } else {
                    Toast.makeText(context, "Сервер вернул ошибку", Toast.LENGTH_SHORT).show();
                }
                progressBarSpinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<OwnEventsResponse> call, Throwable t) {
                ProgressService.showDialogMessage(context, "Ошибка соединения",
                        "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                        2148, true);
            }
        });
    }

    private static void updateListOfOwnEvents(OwnEventsResponse listFromServer, ArrayList<OwnEvent> ownEvents) {
        for (int i = 0; i < listFromServer.getIndex().size(); i++) {
            ownEvents.add(new OwnEvent(
                    1,
                    listFromServer.getIndex().get(i),
                    listFromServer.getLeagueName().get(i),
                    listFromServer.getEventDate().get(i),
                    listFromServer.getEventTime().get(i),
                    listFromServer.getEventName().get(i)
            ));
        }
    }
}
