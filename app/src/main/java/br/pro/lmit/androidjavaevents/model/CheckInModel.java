package br.pro.lmit.androidjavaevents.model;

import com.google.gson.annotations.SerializedName;

public class CheckInModel {

    @SerializedName("eventId")
    private String mEventId;

    @SerializedName("name")
    private String mName;

    @SerializedName("email")
    private String mEmail;

    public CheckInModel(String id, String name, String email) {
        this.mEventId = id;
        this.mName = name;
        this.mEmail = email;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String mEventId) {
        this.mEventId = mEventId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
