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

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;

public class FragmentEcho extends Fragment {

    private BookingViewModel mBookingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_echo, container, false);

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
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.fab5v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentEcho.this)
                        .navigate(R.id.action_FragmentEcho_to_FragmentAlpha);
            }
        });

        view.findViewById(R.id.fab5v2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar rightNow = Calendar.getInstance();
                String hour = Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY));
                if (hour.length() == 1){
                    hour = "0" + hour;
                } if (hour.length() == 0) {
                    hour = "00";
                }
                String minute = Integer.toString(rightNow.get(Calendar.MINUTE));
                if (minute.length() == 1){
                    minute = "0" + minute;
                } if (minute.length() == 0) {
                    minute = "00";
                }
                String entry = hour + ":" + minute;
                Booking booking = new Booking("test user","facility" , entry);
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