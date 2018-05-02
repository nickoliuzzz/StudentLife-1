package com.a_team.studentlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_events_activity);

        setCreateNewOwnEventButtonListener(this);

        progressBarSpinner = findViewById(R.id.loading_spinner_events);
        progressBarSpinner.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler_list_events);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        ownEventsAdapter = new OwnEventsAdapter();
        OwnEvent.getEventItems(this, ownEventsAdapter, recyclerView, progressBarSpinner);
    }

    private void setCreateNewOwnEventButtonListener(final OwnEventsActivity ownEventsActivity) {
        createNewOwnEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownEventsActivity.startActivity(new Intent(ownEventsActivity, CreateEventActivity.class));
            }
        });
    }
}
