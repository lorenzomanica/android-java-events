package br.pro.lmit.androidjavaevents.model;

import com.google.gson.annotations.SerializedName;

public class CuponModel {

    @SerializedName("id")
    private String mId;

    @SerializedName("discount")
    private Integer mDiscount;

    @SerializedName("eventId")
    private String mEventId;

    public CuponModel(String mId, Integer mDiscount, String mEventId) {
        this.mId = mId;
        this.mDiscount = mDiscount;
        this.mEventId = mEventId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public Integer getDiscount() {
        return mDiscount;
    }

    public void setDiscount(Integer mDiscount) {
        this.mDiscount = mDiscount;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String mEventId) {
        this.mEventId = mEventId;
    }
}
