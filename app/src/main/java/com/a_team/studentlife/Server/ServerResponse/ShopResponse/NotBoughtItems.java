package com.a_team.studentlife.Server.ServerResponse.ShopResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotBoughtItems {
    @SerializedName("index")
    @Expose
    private List<Integer> index = null;
    @SerializedName("name")
    @Expose
    private List<String> name = null;
    @SerializedName("price")
    @Expose
    private List<Integer> price = null;
    @SerializedName("description")
    @Expose
    private List<String> description = null;

    public List<Integer> getIndex() {
        return index;
    }

    public void setIndex(List<Integer> index) {
        this.index = index;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<Integer> getPrice() {
        return price;
    }

    public void setPrice(List<Integer> price) {
        this.price = price;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
