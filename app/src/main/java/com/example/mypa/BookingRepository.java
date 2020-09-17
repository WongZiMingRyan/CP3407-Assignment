package com.example.mypa;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

//Define the repository to manage queries for multiple backends
class BookingRepository {

    //Define Dao and the database
    private BookingDao mBookingDao;
    private LiveData<List<Booking>> mAllBookings;

    //Link the Dao to its database
    BookingRepository(Application application) {
        BookingRoomDatabase db = BookingRoomDatabase.getDatabase(application);
        mBookingDao = db.bookingDao();
        mAllBookings = mBookingDao.getBookingsByTime();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Booking>> getAllBookings() {return mAllBookings;}

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Booking booking) {
        BookingRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBookingDao.insert(booking);
            }
        });
    }
}
