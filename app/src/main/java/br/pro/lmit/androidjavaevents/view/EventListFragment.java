package br.pro.lmit.androidjavaevents.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.view.adapter.EventRecyclerAdapter;
import br.pro.lmit.androidjavaevents.viewmodel.EventListViewModel;

public class EventListFragment extends Fragment {

    private EventListViewModel mViewModel;
    private RecyclerView mRecylcer;
    private EventRecyclerAdapter mAdapter;

    public static EventListFragment newInstance() {
        return new EventListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        mRecylcer = view.findViewById(R.id.recycler);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(EventListViewModel.class);

        mAdapter = new EventRecyclerAdapter(mViewModel.getEventList());
        mRecylcer.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecylcer.setLayoutManager(layoutManager);

        mViewModel.getEventList().observe(getViewLifecycleOwner(), list -> {
            mAdapter.notifyDataSetChanged();
        });

        mViewModel.loadEventList();
    }

}
