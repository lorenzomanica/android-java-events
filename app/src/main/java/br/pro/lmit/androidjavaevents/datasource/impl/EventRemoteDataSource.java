package br.pro.lmit.androidjavaevents.datasource.impl;

import java.util.List;

import br.pro.lmit.androidjavaevents.datasource.EventDataSource;
import br.pro.lmit.androidjavaevents.model.EventModel;

public class EventRemoteDataSource implements EventDataSource {

    @Override
    public List<EventModel> getEvents() {
        return null;
    }

    @Override
    public EventModel getEventWithId(int id) {
        return null;
    }

    @Override
    public Void postCheckInAtEvent(int eventId) {
        return null;
    }
}
