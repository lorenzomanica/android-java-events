package br.pro.lmit.androidjavaevents.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventModel {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("image")
    private String mImage;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("price")
    private Double mPrice;

    @SerializedName("latitude")
    private Double mLatitude;

    @SerializedName("longitude")
    private Double mLongitude;

    @SerializedName("date")
    private Long mDate;

    private List<PersonModel> people;
    private List<CuponModel> cupons;

    public EventModel(String id, String title, String img, String descr, Double price, Double lat,
                      Double lng, Long date) {
        this.mId = id;
        this.mTitle = title;
        this.mImage = img;
        this.mDescription = descr;
        this.mPrice = price;
        this.mLatitude = lat;
        this.mLongitude = lng;
        this.mDate = date;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String img) {
        this.mImage = img;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String descr) {
        this.mDescription = descr;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        this.mPrice = price;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double lat) {
        this.mLatitude = lat;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double lng) {
        this.mLongitude = lng;
    }

    public Long getDate() {
        return mDate;
    }

    public void setDate(Long date) {
        this.mDate = date;
    }

    public List<PersonModel> getPeople() {
        return people;
    }

    public void setPeople(List<PersonModel> people) {
        this.people = people;
    }

    public List<CuponModel> getCupons() {
        return cupons;
    }

    public void setCupons(List<CuponModel> cupons) {
        this.cupons = cupons;
    }
}
