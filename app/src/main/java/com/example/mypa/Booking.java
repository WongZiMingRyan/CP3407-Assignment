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
    @ColumnInfo(name = "user")
    private String mBookingUser;

    @NonNull
    //Defines a column in the table
    @ColumnInfo(name = "facility")
    private String mBookingFacility;

    @NonNull
    //Defines a column in the table
    @ColumnInfo(name = "time")
    private String mBookingTime;

    //Defines getter for booking user
    public String getBookingUser(){return this.mBookingUser;}

    //Defines getter for booking facility
    public String getBookingFacility(){return this.mBookingFacility;}

    //Defines getter for booking time
    public String getBookingTime(){return this.mBookingTime;}

    //Defines getter for whole booking entry
    public String getBooking(){
        String bookingEntry = this.mBookingUser + " " + this.mBookingFacility
                + " " + this.mBookingTime;
        return bookingEntry;}


    //Defines a setter
    public Booking(String bookingUser, String bookingFacility, String bookingTime) {
        this.mBookingUser = bookingUser;
        this.mBookingFacility = bookingFacility;
        this.mBookingTime = bookingTime;}


}
