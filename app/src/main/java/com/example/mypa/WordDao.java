package com.example.mypa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//Defines the Dao interface
@Dao
public interface WordDao {

    //Defines how to handle multiple of the same entry
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    //Defines a method to clear all data from the entity
    @Query("DELETE FROM word_table")
    void deleteAll();

    //Defines a method to get all data from entity by ascending order
    @Query("SELECT * from word_table ORDER BY word ASC")
    //Note: the LiveData wrapping here is because the UI to be used is a LiveData class
    LiveData<List<Word>> getAlphabetizedWords();
}
