package com.example.mypa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class FragmentDelta extends Fragment {

    private BookingViewModel mBookingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delta, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.fab4v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentDelta.this)
                        .navigate(R.id.action_FragmentDelta_to_FragmentAlpha);
            }
        });

        view.findViewById(R.id.fab4v2).setOnClickListener(new View.OnClickListener() {
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

        view.findViewById(R.id.fab4v3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentDelta.this)
                        .navigate(R.id.action_FragmentDelta_to_FragmentEcho);
            }
        });
    }
}