package com.a_team.studentlife.Server.ServerResponse.ShopResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyingProductResponse {
    @SerializedName("answer")
    @Expose
    private String answer = null;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
