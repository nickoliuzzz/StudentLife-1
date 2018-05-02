package com.a_team.studentlife.Server.ServerResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OwnEventsResponse {
    @SerializedName("index")
    @Expose
    private List<Integer> index = null;
    @SerializedName("eventName")
    @Expose
    private List<String> eventName = null;
    @SerializedName("eventDate")
    @Expose
    private List<String> eventDate = null;
    @SerializedName("eventTime")
    @Expose
    private List<String> eventTime = null;
    @SerializedName("leagueName")
    @Expose
    private List<String> leagueName = null;

    public List<Integer> getIndex() {
        return index;
    }

    public void setIndex(List<Integer> index) {
        this.index = index;
    }

    public List<String> getEventName() {
        return eventName;
    }

    public void setEventName(List<String> eventName) {
        this.eventName = eventName;
    }

    public List<String> getEventDate() {
        return eventDate;
    }

    public void setEventDate(List<String> eventDate) {
        this.eventDate = eventDate;
    }

    public List<String> getEventTime() {
        return eventTime;
    }

    public void setEventTime(List<String> eventTime) {
        this.eventTime = eventTime;
    }

    public List<String> getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(List<String> leagueName) {
        this.leagueName = leagueName;
    }
}
