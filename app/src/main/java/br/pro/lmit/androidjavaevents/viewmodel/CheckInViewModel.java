package br.pro.lmit.androidjavaevents.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import br.pro.lmit.androidjavaevents.datasource.impl.EventRemoteDataSource;
import br.pro.lmit.androidjavaevents.model.CheckInModel;
import br.pro.lmit.androidjavaevents.repository.EventRepository;

public class CheckInViewModel extends BaseViewModel {

    private final EventRepository mRepository = new EventRepository(new EventRemoteDataSource());

    private final MutableLiveData<String> mName = new MutableLiveData<>("");

    private final MutableLiveData<String> mEmail = new MutableLiveData<>("");

    private final MutableLiveData<Boolean> mFormReady = new MutableLiveData<>(false);

    public CheckInViewModel() {
        Observer<String> mFormReayObserver = s -> {
            mFormReady.setValue(!mName.getValue().isEmpty() && !mEmail.getValue().isEmpty());
        };
        mEmail.observeForever(mFormReayObserver);
        mName.observeForever(mFormReayObserver);
    }

    public LiveData<String> getName() {
        return mName;
    }

    public void setName(String s) {
        mName.postValue(s);
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }

    public void setEmail(String s) {
        mEmail.postValue(s);
    }

    public LiveData<Boolean> getFormReady() {
        return mFormReady;
    }

    public void checkIn(String eventId) {
        mExecutor.execute(() -> {
            mState.postValue(new LoadingState());

            CheckInModel data = new CheckInModel(eventId, mName.getValue(), mEmail.getValue());
            boolean success = mRepository.postCheckInAtEvent(data);
            if (success) {
                mState.postValue(new FinishState());
            } else {
                mState.postValue(new ErrorState(1, "Ocorreu um erro"));
            }
        });
    }

    public void clearFields() {
        mEmail.postValue("");
        mName.postValue("");
        mFormReady.postValue(false);
    }
}
