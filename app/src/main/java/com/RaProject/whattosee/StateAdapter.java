package com.RaProject.whattosee;

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

    public StateAdapter(Context context,
                        int resource,
                        List<Items> states) {

        super(context, resource, states);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.states = states;
    }

    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.ic);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView yearView = (TextView) view.findViewById(R.id.year);
        TextView genrView = (TextView) view.findViewById(R.id.genres);

        Items state = states.get(position);

        flagView.setImageBitmap(state.getItemRes());
        nameView.setText(state.getName());
        yearView.setText(state.getYear());
        genrView.setText(state.getGenres());

        return view;
    }
}