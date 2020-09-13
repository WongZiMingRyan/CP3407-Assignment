package com.example.mypa;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

//Define the repository to manage queries for multiple backends
class WordRepository {

    //Define Dao and the database
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    //Link the Dao to its database
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.insert(word);
            }
        });
    }
}
