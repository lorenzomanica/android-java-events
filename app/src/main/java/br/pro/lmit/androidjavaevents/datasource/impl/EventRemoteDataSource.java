package br.pro.lmit.androidjavaevents.datasource.impl;

import java.util.List;

import br.pro.lmit.androidjavaevents.api.EventApi;
import br.pro.lmit.androidjavaevents.datasource.EventDataSource;
import br.pro.lmit.androidjavaevents.model.CheckInModel;
import br.pro.lmit.androidjavaevents.model.EventModel;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventRemoteDataSource implements EventDataSource {

    public static final String BASE_URL = "http://5b840ba5db24a100142dcd8c.mockapi.io";

    private final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final EventApi mWebService = mRetrofit.create(EventApi.class);

    @Override
    public List<EventModel> getEvents() throws Exception {
        Response<List<EventModel>> response = mWebService.getEvents().execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            return null;
        }
    }

    @Override
    public EventModel getEventWithId(String id) throws Exception {
        Response<EventModel> response = mWebService.getEventWithId(id).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            return null;
        }
    }

    @Override
    public boolean postCheckInAtEvent(CheckInModel data) throws Exception {
        Response<Void> response = mWebService.postCheckIn(data).execute();
        return response.isSuccessful();
    }
}
