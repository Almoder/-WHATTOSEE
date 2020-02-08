package com.example.whattosee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContentAdapter extends ArrayAdapter<ContentContainer> {

    private LayoutInflater inflater;
    private int layout;
    private List<ContentContainer> cont;

    public ContentAdapter (Context context, int resource, List<ContentContainer> states) {
        super(context, resource, states);
        this.cont = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
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
        TextView IMDBView = (TextView) view.findViewById(R.id.ratingIMDB);
        TextView KinoPoiskView = (TextView) view.findViewById(R.id.ratingKinoPoisk);
        TextView producerView = (TextView) view.findViewById(R.id.producer);
        TextView DiscView = (TextView) view.findViewById(R.id.discription1);
        TextView CastView = (TextView) view.findViewById(R.id.cast);

        ContentContainer conts =cont.get(position);

        tatle1View.setText(conts.getName());
        icView.setImageResource(conts.getItemResource());
        tatle2View.setText("Название: " + conts.getName1());
        yearView.setText("Год: "+ conts.getYear());
        countryView.setText("Страна: "+ conts.getCountry());
        genresView.setText("Жанр: " + conts.getGenres());
        DurationView.setText("Длительность: " + conts.getDuration());
        IMDBView.setText("Рейтинг IMDB: "+ conts.getRatingIMDB());
        KinoPoiskView.setText("Рейтинг Кинопоиск: "+conts.getRatingKinoPoisk());
        producerView.setText("Режиссёр: " +conts.getProducer());
        DiscView.setText("Описание: \n "+ conts.getDiscription());
        CastView.setText("В ролях:" + conts.getCast());

        return view;
    }
}