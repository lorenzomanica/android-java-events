package br.pro.lmit.androidjavaevents.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.view.adapter.EventRecyclerAdapter;
import br.pro.lmit.androidjavaevents.viewmodel.BaseViewModel;
import br.pro.lmit.androidjavaevents.viewmodel.EventListViewModel;

public class EventListFragment extends Fragment {

    private EventListViewModel mViewModel;
    private EventRecyclerAdapter mAdapter;
    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipeRefresh;
    private View mEmptyView;

    public static EventListFragment newInstance() {
        return new EventListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(EventListViewModel.class);

        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        ((AppCompatActivity) getActivity()).setSupportActionBar(view.findViewById(R.id.toolbar));

        mEmptyView = view.findViewById(R.id.empty_list);

        mRecycler = view.findViewById(R.id.recycler);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);

        mAdapter = new EventRecyclerAdapter(mViewModel.getEventList());
        mAdapter.setItemEventClickListener(this::showEventDetails);
        mRecycler.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(layoutManager);

        mSwipeRefresh.setOnRefreshListener(() -> {
            mViewModel.loadEventList();
        });

        mViewModel.getEventList().observe(getViewLifecycleOwner(), list -> {
            mAdapter.notifyDataSetChanged();
        });

        mViewModel.getViewModelState().observe(getViewLifecycleOwner(), state -> {
            mSwipeRefresh.setRefreshing(state instanceof BaseViewModel.LoadingState);
            if (state instanceof BaseViewModel.ErrorState) {
                String error = ((BaseViewModel.ErrorState) state).getErrorDescription();
                Snackbar.make(view, error, Snackbar.LENGTH_LONG).show();
            }
            if (state instanceof BaseViewModel.FinishState) {
                Navigation.findNavController(getActivity(), R.id.nav_host)
                        .popBackStack();
            }
            if (state instanceof BaseViewModel.EmptyState) {
                mEmptyView.setVisibility(View.VISIBLE);
                mRecycler.setVisibility(View.GONE);
            } else {
                mEmptyView.setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            }
        });

        if (mViewModel.getViewModelState().getValue() instanceof BaseViewModel.InitState) {
            mViewModel.loadEventList();
        }

        return view;
    }

    private void showEventDetails(String id) {
        Bundle args = new Bundle();
        args.putString(EventDetailFragment.ARGS_EVENT_ID, id);

        Navigation.findNavController(getActivity(), R.id.nav_host)
                .navigate(R.id.eventDetails, args);
    }

}
