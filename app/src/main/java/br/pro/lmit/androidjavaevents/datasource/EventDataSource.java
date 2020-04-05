package br.pro.lmit.androidjavaevents.datasource;

import java.util.List;

import br.pro.lmit.androidjavaevents.model.EventModel;

public interface EventDataSource {

    List<EventModel> getEvents();

    EventModel getEventWithId(int id);

    Void postCheckInAtEvent(int eventId);
}
