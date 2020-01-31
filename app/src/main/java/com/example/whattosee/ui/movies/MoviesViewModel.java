package com.example.whattosee.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MoviesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("###Какой-то список фильмов###");
    }

    public LiveData<String> getText() {
        return mText;
    }
}