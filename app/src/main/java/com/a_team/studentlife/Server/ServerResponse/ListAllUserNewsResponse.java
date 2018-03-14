package com.a_team.studentlife.Server.ServerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListAllUserNewsResponse {

    @SerializedName("index")
    @Expose
    private List<Integer> index = null;
    @SerializedName("description")
    @Expose
    private List<String> description = null;
    @SerializedName("league")
    @Expose
    private List<String> league = null;

    public List<Integer> getIndex() {
        return index;
    }

    public void setIndex(List<Integer> index) {
        this.index = index;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getLeague() {
        return league;
    }

    public void setLeague(List<String> league) {
        this.league = league;
    }

}