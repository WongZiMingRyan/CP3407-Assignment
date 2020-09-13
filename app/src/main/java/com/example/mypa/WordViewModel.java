package com.example.mypa;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//Defines a ViewModel class to present the data
public class WordViewModel extends AndroidViewModel {

    //Define the repository to be used
    private WordRepository mRepository;

    //Define the data to be used
    private LiveData<List<Word>> mAllWords;

    //create new instance of the repository and retrieve the data
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //Define a method to retrieve all data
    LiveData<List<Word>> getAllWords() { return mAllWords; }

    //Define a method to insert data
    public void insert(Word word) { mRepository.insert(word); }
}
