package com.example.mypa;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//Defines a ViewModel class to present the data
public class BookingViewModel extends AndroidViewModel {

    //Define the repository to be used
    private BookingRepository mBookingRepository;

    //Define the data to be used
    private LiveData<List<Booking>> mAllBookings;

    //create new instance of the repository and retrieve the data
    public BookingViewModel (Application application) {
        super(application);
        mBookingRepository = new BookingRepository(application);
        mAllBookings = mBookingRepository.getAllBookings();
    }

    //Define a method to retrieve all data
    LiveData<List<Booking>> getAllBookings() { return mAllBookings; }

    //Define a method to insert data
    public void insert(Booking booking) { mBookingRepository.insert(booking);}
}
