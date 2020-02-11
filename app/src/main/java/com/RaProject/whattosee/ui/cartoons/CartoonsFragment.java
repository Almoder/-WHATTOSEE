package com.RaProject.whattosee.ui.cartoons;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import com.RaProject.whattosee.ContentActivity;
import com.RaProject.whattosee.Items;
import com.RaProject.whattosee.R;
import com.RaProject.whattosee.StateAdapter;
import com.RaProject.whattosee.ui.done.DoneFragment;

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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;
        String INDV = "C";
        switch (position) {
            case 0:
                // подключаем FragmentManager
                FragmentManager fragmentManager = getFragmentManager();

                // Получаем ссылку на второй фрагмент по ID
                DoneFragment fragment2 = (DoneFragment) fragmentManager
                        .findFragmentById(R.id.fragment1);
                if (fragment2 == null || !fragment2.isVisible()) {
                    // запускаем активность
                    Intent intent1 = new Intent(getActivity(), ContentActivity.class);
                    INDV = INDV + position;
                    intent1.putExtra("Part", INDV);
                    startActivity(intent1);

                }
                else { }
                break;
            case 1:
                break;
            default: break;
        }
        //Запускаем активность
    }



    private void setInitialData(){

        //items.add(new Items ("Стальной гигант", "-", R.mipmap.ic_launcher2));
        items.add(new Items ("ВАЛЛ·И", "Жанр: Мультфильмы", R.mipmap.ic_vali));
        //items.add(new Items ("Корпорация монстров", "-", R.mipmap.ic_launcher2));
        //items.add(new Items ("Труп Невесты", "-", R.mipmap.ic_launcher2));

    }

}
