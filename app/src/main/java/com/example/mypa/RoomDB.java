package com.example.mypa;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//add database entities
@Database(entities = {DataClocking.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //Create database instance
    private static RoomDB databaseclocking;
    //Define database name
    private static String DATABASE_NAME = "databaseclocking";

    public synchronized static RoomDB getInstance(Context context){
        //Check if database is empty
        if (databaseclocking == null){
            databaseclocking = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        //return database
        return databaseclocking;
    }
    //Create Dao
    public abstract DaoClocking DaoClocking();


}
