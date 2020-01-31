package com.example.whattosee.ui.shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ShopViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShopViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("###Магазин###");
    }

    public LiveData<String> getText() {
        return mText;
    }
}