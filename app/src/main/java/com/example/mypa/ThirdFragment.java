package com.example.mypa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ThirdFragment extends Fragment {

    private WordViewModel mWordViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        //Define the recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        //Initiate and attach the recyclerView adapter
        final WordListAdapter adapter = new WordListAdapter(view.getContext());
        recyclerView.setAdapter(adapter);
        //Prepare the recyclerViews layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //Initiate the viewModel
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.fab3v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.fab3v2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar rightNow = Calendar.getInstance();
                String hour = Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY));
                if (hour.length() == 1){
                    hour = "0" + hour;
                } if (hour.length() == 0) {
                    hour = "00";
                }
                String minute = Integer.toString(rightNow.get(Calendar.MINUTE));
                if (minute.length() == 1){
                    minute = "0" + minute;
                } if (minute.length() == 0) {
                    minute = "00";
                }
                String entry = "Test user clocked at " + hour + ":" + minute;
                Word word = new Word(entry);
                mWordViewModel.insert(word);
            }
        });
    }

}
