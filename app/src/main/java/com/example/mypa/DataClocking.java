package com.example.mypa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Defining table name
@Entity(tableName = "clocking_data")
public class DataClocking implements Serializable {

    //Create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //Create text column (holds user phone num)
    @ColumnInfo(name = "user")
    private String user;

    //Create text column (holds time and date)
    @ColumnInfo(name = "time")
    private String time;

    //Generate getter and setter


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
