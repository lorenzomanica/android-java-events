package br.pro.lmit.androidjavaevents.repository;

import java.util.Collections;
import java.util.List;

import br.pro.lmit.androidjavaevents.datasource.impl.EventRemoteDataSource;
import br.pro.lmit.androidjavaevents.model.CheckInModel;
import br.pro.lmit.androidjavaevents.model.EventModel;

public class EventRepository {

    private final EventRemoteDataSource mDataSource;

    public EventRepository(EventRemoteDataSource ds) {
        mDataSource = ds;
    }

    public List<EventModel> getAll() {
        try {
            return mDataSource.getEvents();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public EventModel getEventWithId(String id) {
        try {
            return mDataSource.getEventWithId(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean postCheckInAtEvent(CheckInModel data) {
        try {
            return mDataSource.postCheckInAtEvent(data);
        } catch (Exception e) {
            return false;
        }
    }
}
