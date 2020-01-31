package com.example.whattosee.ui.WantToSee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whattosee.R;

public class WantToSeeFragment extends Fragment {

    private WantToSeeViewModel wantToSeeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wantToSeeViewModel =
                ViewModelProviders.of(this).get(WantToSeeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wanttosee, container, false);
        final TextView textView = root.findViewById(R.id.text_wanttosee);
        wantToSeeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}