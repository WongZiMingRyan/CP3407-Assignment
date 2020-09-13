package com.example.mypa;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//This defines a table entity that will hold data temporarily
@Entity(tableName = "word_table")
public class Word {

    //An auto generator selects appropriate primary key ids
    @PrimaryKey

    @NonNull
    //Defines a column in the table
    @ColumnInfo(name = "word")
    private String mWord;

    //Defines a setter
    public Word(String word) {this.mWord = word;}

    //Defines getter
    public String getWord(){return this.mWord;}
}

