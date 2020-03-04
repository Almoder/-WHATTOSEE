package com.RaProject.whattosee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContentAdapter extends ArrayAdapter<ContentContainer> {

    private LayoutInflater inflater;
    private int layout;
    private List<ContentContainer> cont;
    private int aType;

    public ContentAdapter (Context context, int resource, List<ContentContainer> states, int aType) {
        super(context, resource, states);
        this.cont = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.aType = aType;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView tatle1View = (TextView) view.findViewById(R.id.Title_Content);
        ImageView icView = (ImageView) view.findViewById(R.id.ic1);
        TextView tatle2View = (TextView) view.findViewById(R.id.Title_Content1);
        TextView yearView = (TextView) view.findViewById(R.id.Year);
        TextView countryView = (TextView) view.findViewById(R.id.country);
        TextView genresView = (TextView) view.findViewById(R.id.genres);
        TextView DurationView = (TextView) view.findViewById(R.id.duration);
        TextView IMDBView = (TextView) view.findViewById(R.id.ratingDev);
        TextView KinoPoiskView = (TextView) view.findViewById(R.id.ratingKinoPoisk);
        TextView producerView = (TextView) view.findViewById(R.id.producer);
        TextView DiscView = (TextView) view.findViewById(R.id.discription1);
        TextView CastView = (TextView) view.findViewById(R.id.cast);

        ContentContainer conts = cont.get(position);

        tatle1View.setText(conts.getName());
        icView.setImageBitmap(conts.getItemRes());
        tatle2View.setText("Название: " + conts.getName1());
        yearView.setText("Год: "+ conts.getYear());
        countryView.setText("Страна: "+ conts.getCountry());
        genresView.setText("Жанр: " + conts.getGenres());
        DurationView.setText("Длительность: " + conts.getDuration());
        IMDBView.setText("Рейтинг разработчиков: "+ conts.getRatingDev());
        KinoPoiskView.setText("Рейтинг Кинопоиск: "+conts.getRatingKinoPoisk());
        producerView.setText("Режиссёр: " +conts.getProducer());
        DiscView.setText("Описание: \n "+ conts.getDescription());
        CastView.setText("В ролях: " + conts.getCast());

        if (aType >= 0) {
            FloatingActionButton fab1 = view.findViewById(R.id.fab1);
            FloatingActionButton fab2 = view.findViewById(R.id.fab2);
            if (aType == 0){    //если Wanttosee
                fab1.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp));
            }
            else {              //если Saw
                fab2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp));
                fab2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.fabRed));
            }
        }
        return view;
    }
}