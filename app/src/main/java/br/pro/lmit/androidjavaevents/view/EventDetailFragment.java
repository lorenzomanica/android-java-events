package br.pro.lmit.androidjavaevents.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.view.adapter.CuponRecyclerAdapter;
import br.pro.lmit.androidjavaevents.view.adapter.PeopleRecyclerAdapter;
import br.pro.lmit.androidjavaevents.viewmodel.BaseViewModel;
import br.pro.lmit.androidjavaevents.viewmodel.EventDetailViewModel;

public class EventDetailFragment extends Fragment {

    public static final String ARGS_EVENT_ID = "EVENT_ID";

    private EventDetailViewModel mViewModel;

    private CuponRecyclerAdapter mCuponAdapter;
    private PeopleRecyclerAdapter mPeopleAdapter;

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(EventDetailViewModel.class);
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(view.findViewById(R.id.toolbar));
        final ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        final ImageView banner = view.findViewById(R.id.banner);
        final TextView eventName = view.findViewById(R.id.event_name);
        final TextView eventDescription = view.findViewById(R.id.event_description);
        final TextView eventPrice = view.findViewById(R.id.event_price);
        final TextView eventDate = view.findViewById(R.id.event_date);
        final TextView eventLocation = view.findViewById(R.id.event_location);
        final Button checkIn = view.findViewById(R.id.checkin_button);
        final RecyclerView cuponRecycler = view.findViewById(R.id.cupons_recycler);
        final RecyclerView peopleRecyler = view.findViewById(R.id.people_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        cuponRecycler.setLayoutManager(layoutManager);

        mCuponAdapter = new CuponRecyclerAdapter(mViewModel.getEvent());
        cuponRecycler.setAdapter(mCuponAdapter);

        layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        peopleRecyler.setLayoutManager(layoutManager);

        mPeopleAdapter = new PeopleRecyclerAdapter(mViewModel.getEvent());
        peopleRecyler.setAdapter(mPeopleAdapter);

        mViewModel.getEvent().observe(getViewLifecycleOwner(), event -> {
            Picasso.get()
                    .load(event.getImage())
                    .into(banner);

            eventName.setText(event.getTitle());

            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
            eventPrice.setText(formatter.format(event.getPrice()));

            eventDate.setText(DateUtils.formatDateTime(getContext(), event.getDate(),
                    DateUtils.FORMAT_NUMERIC_DATE));

            eventLocation.setText(String.format(Locale.getDefault(), "%,.2f %,.2f", event.getLatitude(), event.getLongitude()));

            eventDescription.setText(event.getDescription());

            checkIn.setOnClickListener(v -> {
                Navigation.findNavController(getActivity(), R.id.nav_host)
                        .navigate(R.id.action_eventDetails_to_checkIn);
            });

            mCuponAdapter.notifyDataSetChanged();
            mPeopleAdapter.notifyDataSetChanged();
        });

        if (getArguments() != null && getArguments().containsKey(ARGS_EVENT_ID)) {
            final String eventId = getArguments().getString(ARGS_EVENT_ID);

            if (mViewModel.getViewModelState().getValue() instanceof BaseViewModel.InitState) {
                mViewModel.loadEvent(eventId);
            }
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_share, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Navigation.findNavController(getActivity(), R.id.nav_host)
                        .popBackStack();
                return true;
            case R.id.share:
                shareEvent(mViewModel.getEvent().getValue().getId());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareEvent(String id) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "http://5b840ba5db24a100142dcd8c.mockapi.io/api/events/" + id);
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }
}
