package com.example.whattosee.ui.Content;

import android.os.Bundle;

import com.example.whattosee.R;
import android.preference.PreferenceFragment;

public class SomeContent extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref1);
    }

}
