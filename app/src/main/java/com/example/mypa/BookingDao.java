package com.example.mypa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//Defines the Dao interface
@Dao
public interface BookingDao {

    //Defines how to handle multiple of the same entry
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Booking booking);

    //Defines a method to clear all data from the entity
    @Query("DELETE FROM booking_table")
    void deleteAll();

    //Defines a method to get all data from entity by ascending order
    @Query("SELECT * from booking_table ORDER BY time ASC")
    //Note: the LiveData wrapping here is because the UI to be used is a LiveData class
    LiveData<List<Booking>> getBookingsByTime();
}
