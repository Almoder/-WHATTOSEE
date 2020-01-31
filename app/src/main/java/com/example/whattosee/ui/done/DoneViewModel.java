package com.example.whattosee.ui.done;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DoneViewModel  extends ViewModel {
    private MutableLiveData<String> mText;

    public DoneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("###Какой-то список того, что посмотрели###");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
