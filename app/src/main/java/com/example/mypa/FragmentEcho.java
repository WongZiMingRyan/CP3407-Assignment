package com.example.mypa;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
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
        final BookingListAdapter adapter = new BookingListAdapter(view.getContext());
        recyclerView.setAdapter(adapter);
        //Prepare the recyclerViews layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //Initiate the viewModel
        mBookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        mBookingViewModel.getAllBookings().observe(getViewLifecycleOwner(), new Observer<List<Booking>>() {
            @Override
            public void onChanged(@Nullable final List<Booking> bookings) {
                // Update the cached copy of the words in the adapter.
                adapter.setBookings(bookings);
            }
        });

        view.findViewById(R.id.fab5v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentEcho.this)
                        .navigate(R.id.action_FragmentEcho_to_FragmentAlpha);
            }
        });
        mDatePicker = view.findViewById(R.id.datePicker1);
        view.findViewById(R.id.fab5v2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                //date entries are inserted as Year/month/day to help with sorting
                String entry = year + "/" + month + "/" + day;
                Booking booking = new Booking("test user booked","facility on" , entry);
                mBookingViewModel.insert(booking);
                Snackbar.make(view, "Your Booking has been submitted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        view.findViewById(R.id.fab5v3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(FragmentEcho.this)
                        .navigate(R.id.action_FragmentEcho_to_FragmentDelta);
            }
        });
    }
}