package br.pro.lmit.androidjavaevents.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class BaseViewModel extends ViewModel {

    protected final Executor mExecutor = Executors.newFixedThreadPool(1);
    protected final MutableLiveData<State> mState = new MutableLiveData<>(new InitState());

    public MutableLiveData<State> getViewModelState() {
        return mState;
    }


    public interface State {
    }

    public static class InitState implements State {
    }

    public static class LoadingState implements State {
    }

    public static class EmptyState implements State {
    }

    public static class ShowingState implements State {
    }

    public static class ErrorState implements State {

        private final Integer mErrorCode;
        private final String mErrorDescription;

        public ErrorState(Integer code, String descr) {
            mErrorCode = code;
            mErrorDescription = descr;
        }

        public Integer getErrorCode() {
            return mErrorCode;
        }

        public String getErrorDescription() {
            return mErrorDescription;
        }
    }

    public static class FinishState implements State {
    }
}
