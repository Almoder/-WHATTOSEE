package com.example.whattosee.ui.cartoons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class CartoonsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CartoonsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("###хер###");
    }

    public LiveData<String> getText() {
        return mText;
    }
}