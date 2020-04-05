package br.pro.lmit.androidjavaevents.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.viewmodel.EventDetailViewModel;

public class EventDetailFragment extends Fragment {

    private EventDetailViewModel mViewModel;

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}
