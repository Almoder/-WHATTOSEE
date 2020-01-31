package com.example.whattosee.ui.serials;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SerialsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SerialsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("###Какой-то список Сериалов###");
    }

    public LiveData<String> getText() {
        return mText;
    }
}