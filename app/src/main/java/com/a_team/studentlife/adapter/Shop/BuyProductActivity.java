package com.a_team.studentlife.adapter.Shop;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.ProgressBars.ProgressService;
import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ShopResponse.BuyingProductResponse;
import com.a_team.studentlife.UserInformation.User;
import com.a_team.studentlife.card_view_filling.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyProductActivity extends AppCompatActivity {
    private TextView productName;
    private TextView productDescription;
    private TextView productLeagueShopName;
    private TextView productIsBought;
    private TextView productPrice;
    private Button buyProductButton;
    private Product product;
    private AnimationDrawable animationDrawable;
    private LinearLayout linearLayout;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_product_activity);
        setTitle("Купить продукт");

        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_buy_product);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

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
        productLeagueShopName.setText(productLeagueShopName.getText() + " " + product.getLeagueShopName());
        productIsBought = (TextView) findViewById(R.id.buy_product_isBought);
        if (product.isBought()) {
            productIsBought.setText(productIsBought.getText() + " куплено");
            productIsBought.setTextColor(Color.GREEN);
        } else {
            productIsBought.setText(productIsBought.getText() + " не куплено");
            productIsBought.setTextColor(Color.RED);
        }
        productPrice = (TextView) findViewById(R.id.buy_product_price);
        productPrice.setText(String.valueOf(productPrice.getText() + " " + product.getProductPrice()));
        buyProductButton = (Button) findViewById(R.id.buy_product_button);
        setBuyProductButtonOnClickListener();
        if (product.isBought())
            buyProductButton.setEnabled(false);
    }

    private void setBuyProductButtonOnClickListener() {
        buyProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService mAPIService = ApiUtils.getAPIService();
                mAPIService.buyProduct(User.getUserInstance().getId(), product.getProductId())
                        .enqueue(new Callback<BuyingProductResponse>() {
                            @Override
                            public void onResponse(Call<BuyingProductResponse> call,
                                                   Response<BuyingProductResponse> response) {
                                if (response.isSuccessful() && response.body().getAnswer().equals("OK")) {
                                    Toast.makeText(BuyProductActivity.this,
                                            "Вы приобрели " + product.getProductName(),
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(BuyProductActivity.this,
                                            response.body().getAnswer(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BuyingProductResponse> call, Throwable t) {
                                ProgressService.showDialogMessage(BuyProductActivity.this,
                                        "Ошибка соединения",
                                        "Проверьте соединение с интернетом",
                                        ProgressDialog.STYLE_SPINNER,
                                        2148, true);
                            }
                        });
            }
        });
    }
}
