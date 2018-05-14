package com.a_team.studentlife.adapter.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.LeagueListElement;
import com.a_team.studentlife.card_view_filling.Product;

public class LeagueShopProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private LeagueShopAdapter leagueShopAdapter;
    private ProgressBar progressBarSpinner;
    private LeagueListElement leagueListElement;
    private TextView userMoneyValue;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_shop_products_activity);
        setTitle("Магазин лиги");
        swipe = (SwipeRefreshLayout) findViewById(R.id.leagueShopSwipeRefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                createLeagueShopScreen();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        createLeagueShopScreen();
    }

    private void createLeagueShopScreen() {
        Intent leagueIntent = getIntent();
        leagueListElement = new LeagueListElement(
                leagueIntent.getIntExtra("leagueIndex", 0),
                leagueIntent.getStringExtra("leagueName"),
                leagueIntent.getIntExtra("subKey", 0));

        userMoneyValue = (TextView) findViewById(R.id.user_league_money);
        recyclerView = findViewById(R.id.recycler_list_products_leagues_shop);
        progressBarSpinner = findViewById(R.id.loading_spinner_store);
        progressBarSpinner.setVisibility(View.VISIBLE);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        leagueShopAdapter = new LeagueShopAdapter();

        Product.getListOfLeagueShopProducts(this,
                leagueShopAdapter,
                recyclerView,
                progressBarSpinner,
                leagueListElement,
                userMoneyValue);
    }
}
