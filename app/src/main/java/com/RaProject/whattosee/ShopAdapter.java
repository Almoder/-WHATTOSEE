package com.RaProject.whattosee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShopAdapter extends ArrayAdapter<Items_shop> {

    private LayoutInflater inflater;
    private int layout;
    private List<Items_shop> states;

    public ShopAdapter(Context context, int resource, List<Items_shop> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.ic_1);
        TextView nameView = (TextView) view.findViewById(R.id.name_1);
        TextView capitalView = (TextView) view.findViewById(R.id.description_1);

        Items_shop state = states.get(position);

        flagView.setImageResource(state.getItemResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getDiscription());

        return view;
    }
}