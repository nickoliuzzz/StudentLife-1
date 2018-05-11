package com.a_team.studentlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.a_team.studentlife.adapter.own_events.OwnEventsAdapter;
import com.a_team.studentlife.card_view_filling.OwnEvent;

public class OwnEventsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private OwnEventsAdapter ownEventsAdapter;
    private ProgressBar progressBarSpinner;
    private FloatingActionButton createNewOwnEventButton;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_events_activity);
        setTitle("Мои события");
        createOwnEventsActivityScreen();
        swipe = (SwipeRefreshLayout) findViewById(R.id.ownEventsSwipeRefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                createOwnEventsActivityScreen();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void createOwnEventsActivityScreen() {
        createNewOwnEventButton = (FloatingActionButton) findViewById(R.id.fab_create_own_event);
        createNewOwnEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnEventsActivity.this, CreateEventActivity.class));
            }
        });
        progressBarSpinner = findViewById(R.id.loading_spinner_events);
        progressBarSpinner.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler_list_events);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        ownEventsAdapter = new OwnEventsAdapter();
        OwnEvent.getEventItems(this, ownEventsAdapter, recyclerView, progressBarSpinner);
    }
}
