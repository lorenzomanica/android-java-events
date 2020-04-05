package br.pro.lmit.androidjavaevents.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class BaseViewModel extends ViewModel {

    protected final Executor mExecutor = Executors.newFixedThreadPool(1);

}
