package com.example.mypa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FragmentEcho extends Fragment {

    private BookingViewModel mBookingViewModel;
    private DatePicker mDatePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_echo, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Define the recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview5v1);
        //Initiate and attach the recyclerView adapter
        final BookingListAdapter bookingListAdapter = new BookingListAdapter(view.getContext());
        recyclerView.setAdapter(bookingListAdapter);
        //Prepare the recyclerViews layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //Initiate the viewModel
        mBookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        mBookingViewModel.getAllBookings().observe(getViewLifecycleOwner(), new Observer<List<Booking>>() {
            @Override
            public void onChanged(@Nullable final List<Booking> bookings) {
                // Update the cached copy of the words in the adapter.
                bookingListAdapter.setBookings(bookings);
            }
        });

        //Define facility and time spinners
        final Spinner spinnerFacility = (Spinner) view.findViewById(R.id.spinner5v1);
        final Spinner spinnerTime = (Spinner) view.findViewById(R.id.spinner5v2);
        // Create ArrayAdapters using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpinnerFacility = ArrayAdapter.createFromResource(view.getContext(),
                R.array.facilities, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterSpinnerTime = ArrayAdapter.createFromResource(view.getContext(),
                R.array.times, android.R.layout.simple_spinner_item);
        // Specify the layouts to use when the lists of choices appear
        adapterSpinnerFacility.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSpinnerTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapters to the spinners
        spinnerFacility.setAdapter(adapterSpinnerFacility);
        spinnerTime.setAdapter(adapterSpinnerTime);

        view.findViewById(R.id.fab5v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentEcho.this)
                        .navigate(R.id.action_FragmentEcho_to_FragmentAlpha);
            }
        });

        mDatePicker = view.findViewById(R.id.datePicker5v1);

        view.findViewById(R.id.fab5v2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the facility and time selected in the spinners
                String facility = spinnerFacility.getSelectedItem().toString();
                String time = spinnerTime.getSelectedItem().toString();
                //Get the day, month, and year set in the date picker
                String day = Integer.toString(mDatePicker.getDayOfMonth());
                String month = Integer.toString(mDatePicker.getMonth());
                String year = Integer.toString(mDatePicker.getYear());
                //Format day and month to maintain 2 digit format and help the sorting
                if (day.length() == 1){
                    day = "0" + day;
                }
                if (month.length() == 1){
                    month = "0" + month;
                }
                //Date entries are inserted as Year/month/day to help with sorting
                String entryTime = year + "/" + month + "/" + day + " at " + time;
                //Try to add the entry into the database
                String entryBooking = entryTime + " reserved " + facility;
                Booking booking = new Booking(entryBooking);
                try {
                    mBookingViewModel.insert(booking);
                    //Create snackbar message showing confirmation
                    Snackbar.make(view, "Your Booking has been submitted",
                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } catch(Exception e) {
                    //Create snackbar message showing confirmation
                    Snackbar.make(view, "Your Booking has failed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });

    }
}