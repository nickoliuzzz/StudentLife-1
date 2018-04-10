package com.a_team.studentlife.adapter.leagues_interface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.CreateLeagueActivity;
import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.LeagueListElement;
import com.a_team.studentlife.card_view_filling.NewsPost;

public class LeagueActivity extends AppCompatActivity {

    private TextView leagueName;
    private LeagueListElement leagueListElement;
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private LeaguesInterfaceAdapter leaguesInterfaceAdapter;
    private Button applyButton;
    private Button createLeagueButton;
    private ProgressBar progressBarSpinner;
    private AnimationDrawable animationDrawable;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_activity);
        setTitle("Новости лиги");

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout_league_activity);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        Intent leagueIntent = getIntent();
        leagueListElement = new LeagueListElement(
                leagueIntent.getIntExtra("leagueIndex", 0),
                leagueIntent.getStringExtra("leagueName"));

        leagueName = (TextView) findViewById(R.id.leagueName);
        leagueName.setText(leagueListElement.getLeagueName());

        applyButton = (Button) findViewById(R.id.applyButton);
        setApplyButtonListener(applyButton);

        createLeagueButton = (Button) findViewById(R.id.createLeagueButton);
        setCreateLeagueButtonListener(createLeagueButton, this);

        progressBarSpinner = findViewById(R.id.loading_spinner_league);
        progressBarSpinner.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.recycler_list_posts_leagues);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        leaguesInterfaceAdapter = new LeaguesInterfaceAdapter();
        NewsPost.getPostItems(this, null, leaguesInterfaceAdapter, recyclerView,
                progressBarSpinner, leagueListElement.getLeagueIndex(), true, leagueListElement);
    }

    private void setCreateLeagueButtonListener(final Button createLeagueButton, final Context context) {
        createLeagueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreateLeagueActivity.class);
                intent.putExtra("leagueName", leagueListElement.getLeagueName());
                intent.putExtra("leagueIndex", leagueListElement.getLeagueIndex());
                context.startActivity(intent);
            }
        });

    }

    private void setApplyButtonListener(final Button applyButton) {
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (applyButton.getText().equals("Подать заявку")) {
                    applyButton.setText("Отозвать заявку");
                    applyButton.setTextColor(Color.RED);
                    Toast.makeText(LeagueActivity.this, "Заявка подана", Toast.LENGTH_SHORT).show();
                } else {
                    applyButton.setText("Подать заявку");
                    applyButton.setTextColor(Color.BLACK);
                    Toast.makeText(LeagueActivity.this, "Заявка отозвана", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

