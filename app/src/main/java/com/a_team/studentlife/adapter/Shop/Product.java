package com.a_team.studentlife.adapter.Shop;

public class Product {
    private int productId;
    private int leagueShopId;
    private int productPrice;
    private String productName;
    private String productDescription;
    private boolean isBought;

    public Product(int productId, int leagueShopId, int productPrice, String productName,
                   String productDescription, boolean isBought) {
        this.productId = productId;
        this.leagueShopId = leagueShopId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.isBought = isBought;
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
}
