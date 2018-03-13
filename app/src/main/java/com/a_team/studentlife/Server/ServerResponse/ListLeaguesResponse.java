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

    public List<Integer> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Integer> indexes) {
        this.indexes = indexes;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("indexes", indexes).append("names", names).toString();
    }
}
