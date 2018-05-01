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
    @SerializedName("peopleNumber")
    @Expose
    private List<Integer> peopleNumber = null;
    @SerializedName("likeNumber")
    @Expose
    private List<Integer> likeNumber = null;
    @SerializedName("eventDate")
    @Expose
    private List<String> eventDate = null;
    @SerializedName("eventTime")
    @Expose
    private List<String> eventTime = null;
    @SerializedName("isLikedByMe")
    @Expose
    private List<Boolean> isLikedByMe = null;

    public List<Boolean> getIsLikedByMe() {
        return isLikedByMe;
    }

    public void setIsLikedByMe(List<Boolean> isLikedByMe) {
        this.isLikedByMe = isLikedByMe;
    }

    public List<Integer> getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(List<Integer> peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public List<Integer> getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(List<Integer> likeNumber) {
        this.likeNumber = likeNumber;
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