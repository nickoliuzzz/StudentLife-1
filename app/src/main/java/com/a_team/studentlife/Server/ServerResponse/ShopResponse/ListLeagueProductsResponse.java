package com.a_team.studentlife.Server.ServerResponse.ShopResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListLeagueProductsResponse {

    @SerializedName("NotBought")
    @Expose
    private NotBoughtItems notBought;
    @SerializedName("BoughtItems")
    @Expose
    private BoughtItems boughtItems;

    public NotBoughtItems getNotBought() {
        return notBought;
    }

    public void setNotBought(NotBoughtItems notBought) {
        this.notBought = notBought;
    }

    public BoughtItems getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(BoughtItems boughtItems) {
        this.boughtItems = boughtItems;
    }
}
