package com.a_team.studentlife.card_view_filling;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a_team.studentlife.ProgressBars.ProgressService;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ShopResponse.BoughtItems;
import com.a_team.studentlife.Server.ServerResponse.ShopResponse.ListLeagueProductsResponse;
import com.a_team.studentlife.Server.ServerResponse.ShopResponse.NotBoughtItems;
import com.a_team.studentlife.UserInformation.User;
import com.a_team.studentlife.adapter.Shop.LeagueShopAdapter;
import com.a_team.studentlife.adapter.Shop.LeagueShopProductsActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product {
    private int productId;
    private int leagueShopId;
    private int productPrice;
    private String leagueShopName;
    private String productName;
    private String productDescription;
    private boolean isBought;
    public static ArrayList<Product> products = new ArrayList<>();

    public Product(int productId, int leagueShopId, String leagueShopName, int productPrice, String productName,
                   String productDescription, boolean isBought) {
        this.productId = productId;
        this.leagueShopId = leagueShopId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.leagueShopName = leagueShopName;
        this.isBought = isBought;
    }

    public String getLeagueShopName() {
        return leagueShopName;
    }

    public void setLeagueShopName(String leagueShopName) {
        this.leagueShopName = leagueShopName;
    }

    public boolean isBought() {
        return isBought;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLeagueShopId() {
        return leagueShopId;
    }

    public void setLeagueShopId(int leagueShopId) {
        this.leagueShopId = leagueShopId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public static void getListOfLeagueShopProducts(final LeagueShopProductsActivity leagueShopProductsActivity,
                                                   final LeagueShopAdapter leagueShopAdapter,
                                                   final RecyclerView recyclerView,
                                                   final ProgressBar progressBarSpinner,
                                                   final LeagueListElement leagueListElement,
                                                   final TextView headerTextView) {
        if (products.size() != 0)
            products.clear();

        APIService mAPIService = ApiUtils.getAPIService();
        mAPIService.getListOfLeagueShopProducts(User.getUserInstance().getId(),
                leagueListElement.getLeagueIndex()).enqueue(new Callback<ListLeagueProductsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ListLeagueProductsResponse> call,
                                   Response<ListLeagueProductsResponse> response) {
                if (response.isSuccessful()) {
                    ListLeagueProductsResponse listLeagueProductsResponse = response.body();
                    updateListOfLeagueShopProduct(listLeagueProductsResponse, products, leagueListElement);
                    headerTextView.setText(headerTextView.getText() +
                            " " + leagueListElement.getLeagueName() + " " +
                            String.valueOf(User.getUserInstance().getTempLeagueMoney()));
                    leagueShopAdapter.addAllProducts(products);
                    recyclerView.setAdapter(leagueShopAdapter);
                } else {
                    Toast.makeText(leagueShopProductsActivity,
                            "Сервер вернул ошибку",
                            Toast.LENGTH_SHORT).show();
                }
                progressBarSpinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ListLeagueProductsResponse> call, Throwable t) {
                ProgressService.showDialogMessage(leagueShopProductsActivity, "Ошибка соединения",
                        "Проверьте соединение с интернетом", ProgressDialog.STYLE_SPINNER,
                        2148, true);
            }
        });
    }

    private static void updateListOfLeagueShopProduct(ListLeagueProductsResponse listLeagueProductsResponse,
                                                      ArrayList<Product> products,
                                                      LeagueListElement leagueListElement) {

        User.getUserInstance().setTempLeagueMoney(listLeagueProductsResponse.getCurrency());

        NotBoughtItems notBoughtItems = listLeagueProductsResponse.getNotBought();
        for (int i = 0; i < notBoughtItems.getIndex().size(); i++) {
            products.add(new Product(notBoughtItems.getIndex().get(i),
                    leagueListElement.getLeagueIndex(),
                    leagueListElement.getLeagueName(),
                    notBoughtItems.getPrice().get(i),
                    notBoughtItems.getName().get(i),
                    notBoughtItems.getDescription().get(i),
                    false));
        }
        BoughtItems boughtItems = listLeagueProductsResponse.getBoughtItems();
        for (int i = 0; i < boughtItems.getIndex().size(); i++) {
            products.add(new Product(boughtItems.getIndex().get(i),
                    leagueListElement.getLeagueIndex(),
                    leagueListElement.getLeagueName(),
                    boughtItems.getPrice().get(i),
                    boughtItems.getName().get(i),
                    boughtItems.getDescription().get(i),
                    true));
        }
    }
}
