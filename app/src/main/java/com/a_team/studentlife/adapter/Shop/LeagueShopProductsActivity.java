package com.a_team.studentlife.adapter.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.LeagueListElement;

public class LeagueShopProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private LeagueShopAdapter leagueShopAdapter;
    private ProgressBar progressBarSpinner;
    private LeagueListElement leagueListElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_shop_products_activity);

        Intent leagueIntent = getIntent();
        leagueListElement = new LeagueListElement(
                leagueIntent.getIntExtra("leagueIndex", 0),
                leagueIntent.getStringExtra("leagueName"));

        recyclerView = findViewById(R.id.recycler_list_products_leagues_shop);
        progressBarSpinner = findViewById(R.id.loading_spinner_store);
        progressBarSpinner.setVisibility(View.VISIBLE);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);

        Product.getListOfLeagueShopProducts(this,
                leagueShopAdapter,
                recyclerView,
                progressBarSpinner,
                leagueListElement);
    }
}
