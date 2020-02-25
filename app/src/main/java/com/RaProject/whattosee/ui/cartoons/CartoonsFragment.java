package com.RaProject.whattosee.ui.cartoons;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import com.RaProject.whattosee.ContentActivity;
import com.RaProject.whattosee.DatabaseHelper;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartoonsFragment extends ListFragment {
    //
    //private DatabaseHelper helper;
    //private SQLiteDatabase maindb;
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
                Toast.makeText(getContext(),
                        "onAdLoaded()",
                        Toast.LENGTH_SHORT).show();
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
                .build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cartoons, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        int INDV = items.get(position).getKey();
        FragmentManager fragmentManager = getFragmentManager();
        DoneFragment fragment2 = (DoneFragment) fragmentManager.findFragmentById(R.id.fragment1);
        if (fragment2 == null || !fragment2.isVisible()) {
            if (interstitialAd != null && interstitialAd.isLoaded()) interstitialAd.show();
            else {
                Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
                AdRequest adRequest = new AdRequest.Builder()
                        .build();
                interstitialAd.loadAd(adRequest);
            }
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra("Part", INDV);
            intent.putExtra("aType", -1);
            startActivity(intent);
        }
    }

    private void setInitialData() {
        SQLiteDatabase maindb;
        DatabaseHelper helper = new DatabaseHelper(getContext(), "content.db");
        try { helper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase"); }
        try { maindb = helper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException; }
        Cursor cursor = maindb.rawQuery("select _Key, Image, Title, Year from MainTable where _Key in (select * from Cartoons)", null);
        Cursor genres = maindb.rawQuery("select * from GenresKeys where _Key in (select * from Cartoons)", null);
        cursor.moveToFirst();
        genres.moveToFirst();
        while (!cursor.isAfterLast()) {
            String genresTmp = new String();
            for(int i = 1; i < 20; i++)
                if(genres.getString(i) != null) genresTmp += genres.getString(i);
            items.add(new Items(cursor.getInt(0), cursor.getBlob(1),
                    cursor.getString(2), cursor.getString(3), genresTmp));
            cursor.moveToNext();
            genres.moveToNext();
        }
        cursor.close();
        maindb.close();
        helper.close();
    }
}
