package br.pro.lmit.androidjavaevents.model;

public class EventModel {

    private final int mId;
    private final String mName;

    public EventModel(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
