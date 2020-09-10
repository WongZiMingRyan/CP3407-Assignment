package com.example.mypa;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE

@Dao
public interface DaoClocking {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(DataClocking DataClocking);

    //Delete query
    @Delete
    void delete(DataClocking DataClocking);

    //Delete all query
    @Delete
    void reset(List<DataClocking> DataClocking);

    //Update query
    @Query("UPDATE clocking_data SET user = :sUser, time = :sTime WHERE ID = :sID")
    void update(int sID, String sUser, String sTime);

    //Get all data query
    @Query("SELECT * FROM clocking_data")
    List<DataClocking> getAll();

}
