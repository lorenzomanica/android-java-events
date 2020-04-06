package br.pro.lmit.androidjavaevents.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;

import br.pro.lmit.androidjavaevents.datasource.impl.EventRemoteDataSource;
import br.pro.lmit.androidjavaevents.model.EventModel;
import br.pro.lmit.androidjavaevents.repository.EventRepository;

public class EventListViewModel extends BaseViewModel {

    private final EventRepository mRepository = new EventRepository(new EventRemoteDataSource());

    private final MutableLiveData<List<EventModel>> mEventList =
            new MutableLiveData<>(Collections.emptyList());

    public LiveData<List<EventModel>> getEventList() {
        return mEventList;
    }

    public void loadEventList() {
        mExecutor.execute(() -> {
            mState.postValue(new LoadingState());
            List<EventModel> data = mRepository.getAll();
            mEventList.postValue(data);
            if (data == null || data.isEmpty()) {
                mState.postValue(new EmptyState());
            } else {
                mState.postValue(new ShowingState());
            }
        });
    }


}
