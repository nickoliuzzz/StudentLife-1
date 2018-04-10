package com.a_team.studentlife.adapter.Shop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.Product;

public class BuyProductActivity extends AppCompatActivity {
    private TextView productName;
    private TextView productDescription;
    private TextView productLeagueShopName;
    private TextView productIsBought;
    private TextView productPrice;
    private Button buyProductButton;
    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_product_activity);

        Intent productInfIntent = getIntent();
        product = new Product(productInfIntent.getIntExtra("productIndex", 0),
                productInfIntent.getIntExtra("productLeagueShopId", 0),
                productInfIntent.getStringExtra("productLeagueShopName"),
                productInfIntent.getIntExtra("productPrice", 0),
                productInfIntent.getStringExtra("productName"),
                productInfIntent.getStringExtra("productDescription"),
                productInfIntent.getBooleanExtra("productIsBought", false));


        productName = (TextView) findViewById(R.id.buy_product_name);
        productName.setText(product.getProductName());
        productDescription = (TextView) findViewById(R.id.buy_product_description);
        productDescription.setText(product.getProductDescription());
        productLeagueShopName = (TextView) findViewById(R.id.buy_product_league_shop_name);
        productLeagueShopName.setText(product.getLeagueShopName());
        productIsBought = (TextView) findViewById(R.id.buy_product_isBought);
        if (product.isBought()) {
            productIsBought.setText("Уже куплено");
            productIsBought.setTextColor(Color.GREEN);
        } else {
            productIsBought.setText("Не куплено");
            productIsBought.setTextColor(Color.RED);
        }
        productPrice = (TextView) findViewById(R.id.buy_product_price);
        productPrice.setText(String.valueOf(product.getProductPrice()));
        buyProductButton = (Button) findViewById(R.id.buy_product_button);
        setBuyProductButtonOnClickListener();
    }

    private void setBuyProductButtonOnClickListener() {
        buyProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuyProductActivity.this,
                        "Отправлен запрос",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
