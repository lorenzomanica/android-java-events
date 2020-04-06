package br.pro.lmit.androidjavaevents.datasource;

import java.util.List;

import br.pro.lmit.androidjavaevents.model.CheckInModel;
import br.pro.lmit.androidjavaevents.model.EventModel;

public interface EventDataSource {

    List<EventModel> getEvents() throws Exception;

    EventModel getEventWithId(String id) throws Exception;

    boolean postCheckInAtEvent(CheckInModel data) throws Exception;
}
