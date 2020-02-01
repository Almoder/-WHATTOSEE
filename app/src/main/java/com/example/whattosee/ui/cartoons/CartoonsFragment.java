package com.example.whattosee.ui.cartoons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.ListFragment;

import com.example.whattosee.Items;
import com.example.whattosee.R;
import com.example.whattosee.StateAdapter;

import java.util.ArrayList;
import java.util.List;


public class CartoonsFragment extends ListFragment {


    private List<Items> items = new ArrayList();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setInitialData();

        StateAdapter stateAdapter = new StateAdapter(getActivity(), R.layout.list_what, items);

        setListAdapter(stateAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cartoons, container, false);
    }

    private void setInitialData(){

        items.add(new Items ("Стальной гигант", "-", R.mipmap.ic_launcher2));
        items.add(new Items ("ВАЛЛ·И", "-", R.mipmap.ic_launcher2));
        items.add(new Items ("Корпорация монстров", "-", R.mipmap.ic_launcher2));
        items.add(new Items ("Труп Невесты", "-", R.mipmap.ic_launcher2));

    }

}
