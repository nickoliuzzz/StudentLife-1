package com.a_team.studentlife.Server.ServerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListLeagueProductsResponse {
    @SerializedName("index")
    @Expose
    private List<Integer> index = null;
    @SerializedName("productName")
    @Expose
    private List<String> productName = null;
    @SerializedName("price")
    @Expose
    private List<Integer> price = null;
    @SerializedName("isBought")
    @Expose
    private List<Boolean> isBought = null;
    @SerializedName("description")
    @Expose
    private List<String> description = null;

    public List<Integer> getIndex() {
        return index;
    }

    public void setIndex(List<Integer> index) {
        this.index = index;
    }

    public List<String> getProductName() {
        return productName;
    }

    public void setProductName(List<String> productName) {
        this.productName = productName;
    }

    public List<Integer> getPrice() {
        return price;
    }

    public void setPrice(List<Integer> price) {
        this.price = price;
    }

    public List<Boolean> getIsBought() {
        return isBought;
    }

    public void setIsBought(List<Boolean> isBought) {
        this.isBought = isBought;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
