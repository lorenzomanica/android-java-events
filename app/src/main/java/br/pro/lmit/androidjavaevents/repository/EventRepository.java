package br.pro.lmit.androidjavaevents.repository;

import java.util.List;

import br.pro.lmit.androidjavaevents.datasource.impl.EventRemoteDataSource;
import br.pro.lmit.androidjavaevents.model.EventModel;

public class EventRepository {

    private final EventRemoteDataSource mDataSource;

    public EventRepository(EventRemoteDataSource ds) {
        mDataSource = ds;
    }

    public List<EventModel> getAll() {
        return mDataSource.getEvents();
    }
}
