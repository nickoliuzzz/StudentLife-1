package com.a_team.studentlife.Server.ServerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("id")
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RegistrationResponse withType(String type) {
        this.type = type;
        return this;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public RegistrationResponse withError(String error) {
        this.error = error;
        return this;
    }
}
