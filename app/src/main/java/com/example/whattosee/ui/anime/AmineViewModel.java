package com.example.whattosee.ui.anime;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AmineViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AmineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("###Какой-то список Аниме###");
    }

    public LiveData<String> getText() {
        return mText;
    }
}