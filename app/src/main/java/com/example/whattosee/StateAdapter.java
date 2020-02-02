package com.example.whattosee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StateAdapter extends ArrayAdapter<Items> {

    private LayoutInflater inflater;
    private int layout;
    private List<Items> states;

    public StateAdapter(Context context, int resource, List<Items> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.ic);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView capitalView = (TextView) view.findViewById(R.id.discription);

        Items state = states.get(position);

        flagView.setImageResource(state.getItemResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getDiscription());

        return view;
    }
}