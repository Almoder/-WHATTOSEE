package com.RaProject.whattosee.ui.WantToSee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WantToSeeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WantToSeeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("###Какой-то список того, что хочется посмотреть###");
    }

    public LiveData<String> getText() {
        return mText;
    }
}