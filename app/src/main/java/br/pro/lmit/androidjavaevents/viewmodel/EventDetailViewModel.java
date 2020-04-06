package br.pro.lmit.androidjavaevents.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.pro.lmit.androidjavaevents.datasource.impl.EventRemoteDataSource;
import br.pro.lmit.androidjavaevents.model.EventModel;
import br.pro.lmit.androidjavaevents.repository.EventRepository;

public class EventDetailViewModel extends BaseViewModel {

    private final EventRepository mRepository = new EventRepository(new EventRemoteDataSource());

    private final MutableLiveData<EventModel> mEvent = new MutableLiveData<>();

    public LiveData<EventModel> getEvent() {
        return mEvent;
    }

    public void loadEvent(@NonNull final String id) {
        mExecutor.execute(() -> {
            mState.postValue(new LoadingState());
            EventModel data = mRepository.getEventWithId(id);
            mEvent.postValue(data);
            if (data == null) {
                mState.postValue(new ErrorState(1, "Ocorreu um erro"));
            } else {
                mState.postValue(new ShowingState());
            }
        });
    }
}
