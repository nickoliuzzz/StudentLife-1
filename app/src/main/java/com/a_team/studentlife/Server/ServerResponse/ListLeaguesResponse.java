package com.a_team.studentlife.Server.ServerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListLeaguesResponse {
    @SerializedName("indexes")
    @Expose
    private List<Integer> indexes = null;
    @SerializedName("names")
    @Expose
    private List<String> names = null;
    @SerializedName("subKey")
    @Expose
    private List<Integer> subKey = null;

    public List<Integer> getSubKey() {
        return subKey;
    }

    public void setSubKey(List<Integer> subKey) {
        this.subKey = subKey;
    }

    public List<Integer> getIndexes() {
        return this.indexes;
    }

    public List<String> getNames() {
        return this.names;
    }

    public void setIndexes(List<Integer> indexes) {
        this.indexes = indexes;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
