package br.pro.lmit.androidjavaevents.api;

import java.util.List;

import br.pro.lmit.androidjavaevents.model.CheckInModel;
import br.pro.lmit.androidjavaevents.model.EventModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventApi {

    @GET("/api/events")
    public Call<List<EventModel>> getEvents();

    @GET("/api/events/{id}")
    public Call<EventModel> getEventWithId(@Path("id") String id);

    @POST("/api/checkin")
    public Call<Void> postCheckIn(@Body CheckInModel checkin);

}
