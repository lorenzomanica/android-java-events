package br.pro.lmit.androidjavaevents.model;

import com.google.gson.annotations.SerializedName;

public class PersonModel {

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("eventId")
    private String mEventId;

    public PersonModel(String id, String name, String pic, String event) {
        this.mId = id;
        this.mName = name;
        this.mPicture = pic;
        this.mEventId = event;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String pic) {
        this.mPicture = pic;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String event) {
        this.mEventId = event;
    }
}