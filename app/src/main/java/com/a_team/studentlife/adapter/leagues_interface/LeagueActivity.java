package com.a_team.studentlife.adapter.leagues_interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.R;
import com.a_team.studentlife.adapter.news.NewsAdapter;
import com.a_team.studentlife.card_view_filling.LeagueListElement;
import com.a_team.studentlife.card_view_filling.NewsPost;

public class LeagueActivity extends AppCompatActivity {

    private TextView leagueName;
    private LeagueListElement leagueListElement;
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private LeaguesInterfaceAdapter leaguesInterfaceAdapter;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_activity);
        Intent leagueIntent = getIntent();
        leagueListElement = new LeagueListElement(
                leagueIntent.getIntExtra("leagueIndex", 0),
                leagueIntent.getStringExtra("leagueName"));

        leagueName = (TextView) findViewById(R.id.leagueName);
        leagueName.setText(leagueListElement.getLeagueName());

        applyButton = (Button) findViewById(R.id.applyButton);
        setApplyButtonListener(applyButton);


        recyclerView = findViewById(R.id.recycler_list_posts_leagues);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        leaguesInterfaceAdapter = new LeaguesInterfaceAdapter();
        leaguesInterfaceAdapter.addAllLeagueNews(NewsPost.getPostItems());
        recyclerView.setAdapter(leaguesInterfaceAdapter);
    }

    private void setApplyButtonListener(final Button applyButton) {
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (applyButton.getText().equals("Подать заявку")) {
                    applyButton.setText("Отозвать заявку");
                    Toast.makeText(LeagueActivity.this, "Заявка подана", Toast.LENGTH_SHORT).show();
                } else {
                    applyButton.setText("Подать заявку");
                    Toast.makeText(LeagueActivity.this, "Заявка отозвана", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

