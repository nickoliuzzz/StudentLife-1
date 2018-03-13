package com.studentlife.studentlife.RegistrationAndAuthentication.Server.TestResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {

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

    public ServerResponse withType(String type) {
        this.type = type;
        return this;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ServerResponse withError(String error) {
        this.error = error;
        return this;
    }

}