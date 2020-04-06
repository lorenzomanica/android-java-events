package br.pro.lmit.androidjavaevents.view;

import android.app.Service;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import br.pro.lmit.androidjavaevents.R;
import br.pro.lmit.androidjavaevents.viewmodel.BaseViewModel;
import br.pro.lmit.androidjavaevents.viewmodel.CheckInViewModel;

public class CheckInFragment extends Fragment {

    private CheckInViewModel mViewModel;

    private String eventId;

    public static CheckInFragment newInstance() {
        return new CheckInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null
                && getArguments().containsKey(EventDetailFragment.ARGS_EVENT_ID)) {
            eventId = getArguments().getString(EventDetailFragment.ARGS_EVENT_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CheckInViewModel.class);

        View view = inflater.inflate(R.layout.fragment_check_in, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        final Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.check_in);
        activity.setSupportActionBar(toolbar);
        final ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);


        final TextInputEditText nameField = view.findViewById(R.id.name_field);
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setName(s.toString());
            }
        });

        final TextInputEditText emailField = view.findViewById(R.id.email_field);
        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setEmail(s.toString());
            }
        });

        final Button checkInButton = view.findViewById(R.id.checkin_button);
        checkInButton.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Service.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            mViewModel.checkIn(eventId);
        });

        final Button clearButton = view.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> {
            nameField.setText("");
            emailField.setText("");
            mViewModel.clearFields();
        });

        mViewModel.getFormReady().observe(getViewLifecycleOwner(), checkInButton::setEnabled);
        mViewModel.getViewModelState().observe(getViewLifecycleOwner(), state -> {
            if (state instanceof BaseViewModel.FinishState) {
                Snackbar.make(view, getString(R.string.check_in_successful), Snackbar.LENGTH_LONG).show();
                Navigation.findNavController(getActivity(), R.id.nav_host)
                        .popBackStack(R.id.eventList, false);
            }
            if (state instanceof BaseViewModel.ErrorState) {
                String error = ((BaseViewModel.ErrorState) state).getErrorDescription();
                Snackbar.make(view, error, Snackbar.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Navigation.findNavController(getActivity(), R.id.nav_host)
                        .popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
