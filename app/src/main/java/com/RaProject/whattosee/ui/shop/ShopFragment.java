package com.RaProject.whattosee.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import com.RaProject.whattosee.Cast;
import com.RaProject.whattosee.Items;
import com.RaProject.whattosee.Items_shop;
import com.RaProject.whattosee.R;
import com.RaProject.whattosee.ShopAdapter;
import com.RaProject.whattosee.ShopMoney;
import com.RaProject.whattosee.StateAdapter;
import com.RaProject.whattosee.Watch;
import com.RaProject.whattosee.ui.done.DoneFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends ListFragment {


    private List<Items_shop> items = new ArrayList();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setInitialData();
        ShopAdapter stateAdapter = new ShopAdapter(getActivity(), R.layout.list_shoping, items);
        setListAdapter(stateAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;
        switch (position) {
            case 0: {
                FragmentManager fragmentManager = getFragmentManager();

                // Получаем ссылку на второй фрагмент по ID
                DoneFragment fragment2 = (DoneFragment) fragmentManager
                        .findFragmentById(R.id.fragment3);
                if (fragment2 == null || !fragment2.isVisible()) {
                    // запускаем активность
                    Intent intent1 = new Intent(getActivity(), ShopMoney.class);
                    intent1.putExtra("buttonIndex", position);
                    startActivity(intent1);
                } else {
                }
            }
            break;
            case 2:
            {
                // подключаем FragmentManager
                FragmentManager fragmentManager = getFragmentManager();

                // Получаем ссылку на второй фрагмент по ID
                DoneFragment fragment2 = (DoneFragment) fragmentManager
                        .findFragmentById(R.id.fragment2);
                if (fragment2 == null || !fragment2.isVisible()) {
                    // запускаем активность
                    Intent intent1 = new Intent(getActivity(), Watch.class);
                    intent1.putExtra("buttonIndex", position);
                    startActivity(intent1);
                } else {
                }
        }
                break;
            case 1: {
                // подключаем FragmentManager
                FragmentManager fragmentManager = getFragmentManager();

                // Получаем ссылку на второй фрагмент по ID
                DoneFragment fragment2 = (DoneFragment) fragmentManager
                        .findFragmentById(R.id.fragment4);
                if (fragment2 == null || !fragment2.isVisible()) {
                    // запускаем активность
                    Intent intent1 = new Intent(getActivity(), Cast.class);
                    intent1.putExtra("buttonIndex", position);
                    startActivity(intent1);
                } else {
                }
            }
                break;
            default: break;
        }
        //Запускаем активность
    }



    private void setInitialData(){


        items.add(new Items_shop ("Купить монеты", "Покупайте монеты за деньги", R.mipmap.ic_launcher2));
        items.add(new Items_shop ("Купить Темы", "Покупайте Темы за монеты", R.mipmap.ic_launcher2));
        items.add(new Items_shop ("Получить монеты", "Посмотрите рекламу чтоб получить монеты", R.mipmap.ic_launcher2));

    }

}