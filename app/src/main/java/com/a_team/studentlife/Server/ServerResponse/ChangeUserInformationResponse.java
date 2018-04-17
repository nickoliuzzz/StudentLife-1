package com.a_team.studentlife.Server.ServerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeUserInformationResponse {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("error")
    @Expose
    private String error;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
