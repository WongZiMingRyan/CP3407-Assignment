package com.example.mypa;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//This defines a table entity that will hold booking data temporarily
@Entity(tableName = "booking_table")
public class Booking {

    //An auto generator selects appropriate primary key ids
    @PrimaryKey

    @NonNull
    //Defines a column in the table
    @ColumnInfo(name = "booking")
    private String mBooking;

    //Defines getter for whole booking entry
    public String getBooking(){
        return this.mBooking;}

    //Defines a setter
    public Booking(String booking) {
        this.mBooking = booking;}
}
