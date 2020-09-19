package com.example.mypa;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Defines the database to hold the data between opening and closing
@Database(entities = {Booking.class}, version = 1, exportSchema = false)
public abstract class BookingRoomDatabase extends RoomDatabase {

    //Select the Dao to be used in data handling
    public abstract BookingDao bookingDao();

    //Define required configurations
    private static volatile BookingRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Define method to create the database when it is accessed for the first time,
    // and only 1 instance should exist at any 1 time.
    static BookingRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookingRoomDatabase.class, "booking_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}