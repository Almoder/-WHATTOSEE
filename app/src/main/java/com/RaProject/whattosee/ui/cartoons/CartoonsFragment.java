package com.RaProject.whattosee.ui.cartoons;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import com.RaProject.whattosee.ContentActivity;
import com.RaProject.whattosee.Items;
import com.RaProject.whattosee.R;
import com.RaProject.whattosee.StateAdapter;
import com.RaProject.whattosee.ui.done.DoneFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class CartoonsFragment extends ListFragment {

    private InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-9595963256137742/3560548201";

    private List<Items> items = new ArrayList();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setInitialData();
        StateAdapter stateAdapter = new StateAdapter(getActivity(), R.layout.list_what, items);
        setListAdapter(stateAdapter);
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(getContext());
        // Defined in res/values/strings.xml

        interstitialAd.setAdUnitId(AD_UNIT_ID);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(getContext(), "onAdLoaded()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getActivity(),
                        "onAdFailedToLoad() with error code: " + errorCode,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {

            }
        });

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("8517241ED22D3087DBF2790B07628BED")
                .build();
        interstitialAd.loadAd(adRequest);


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
                    if (interstitialAd != null && interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
                        AdRequest adRequest = new AdRequest.Builder()
                                .build();
                        interstitialAd.loadAd(adRequest);

                    }
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
