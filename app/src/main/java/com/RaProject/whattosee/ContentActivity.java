package com.RaProject.whattosee;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {

    private AdView adView;
    private List<ContentContainer> items = new ArrayList();
    ListView countriesList;
    String key_s;
    int IDnTF, key_i, aType;
    Intent intent;
    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        args = intent.getExtras();
        aType = args.getInt("aType");
        setContentView(R.layout.activity_content_activ);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setInitialData();
        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);
        // создаем адаптер
        ContentAdapter stateAdapter = new ContentAdapter(this, R.layout.conetent_view, items, aType);
        countriesList.setAdapter(stateAdapter);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        adView = findViewById(R.id.ad_view3);

        AdRequest adRequest = new AdRequest.Builder().build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
        // слушатель выбора в списке


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    private String vali()
    {
        String disk;
        disk = "Робот ВАЛЛ·И из года в год прилежно трудится на опустевшей Земле, очищая нашу планету от гор мусора, которые оставили после себя улетевшие в космос люди. Он и не представляет, что совсем скоро произойдут невероятные события, благодаря которым он встретит друзей, поднимется к звездам и даже сумеет изменить к лучшему своих бывших хозяев, совсем позабывших родную Землю.";
        return disk;
    }

    private void setInitialData(){
        key_i = args.getInt("Part");
        key_s = Integer.toString(args.getInt("Part"));
        SQLiteDatabase maindb;
        /*try {
            maindb = getBaseContext().openOrCreateDatabase("content.db", MODE_PRIVATE, null);
        } catch (SQLException mSQLException) {
            throw new Error("DatabaseIsNotOpened!");
        }*/
        DatabaseHelper helper = new DatabaseHelper(this, "content.db");
        try {
            helper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            maindb = helper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        //String[] selectionArgs = {key};
        Cursor entry = maindb.rawQuery("SELECT * FROM MainTable WHERE _Key = " + key_s, null);
        Cursor genres = maindb.rawQuery("SELECT * FROM GenresKeys WHERE _Key = " + key_s, null);
        items.add(new ContentContainer(entry, genres));
        entry.close();
        genres.close();
        maindb.close();
        helper.close();
        //Тут нужно ...flush()
        //items.add(new ContentContainer ("ВАЛЛ·И", "ВАЛЛ·И", "2008", "США","Фантаскика, Приключения, Семейный",
        //        "92 мин", "8.4", "8.3", "Эндрю Стэнтон", vali(), " Бен Бертт, Элисса Найт, Джефф Гарлин, Фред Уиллард, Джон Ратценбергер",
        //        R.drawable.vali));
        //items.add(new Items ("Корпорация монстров", "-", R.mipmap.ic_launcher2));
        //items.add(new Items ("Труп Невесты", "-", R.mipmap.ic_launcher2));
    }

    public void wantOnClick(View view) {
        DatabaseHelper helper = new DatabaseHelper(this, "content.db");
        SQLiteDatabase maindb;
        try {
            helper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            maindb = helper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        if (aType == 0) {
                maindb.execSQL("delete from Wanttowatch where _Key = " + key_s);
                maindb.close();
                helper.close();
                finish();
                getFragmentManager().popBackStack();
                System.gc();
        }
        else {
            try {
                Cursor cursor = maindb.rawQuery("select * from Wanttowatch where _Key = " + key_s, null);
                if (cursor.moveToFirst()) {
                    maindb.close();
                    helper.close();
                    return;
                }
            } catch (SQLException mSQLException) {
                throw mSQLException;
            }
            maindb.execSQL("insert into Wanttowatch values (" + key_i + ")");
            view.setEnabled(false);
            maindb.close();
            helper.close();
        }
    }

    public void sawOnClick(View view) {
        DatabaseHelper helper = new DatabaseHelper(this, "content.db");
        SQLiteDatabase maindb;
        try {
            helper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            maindb = helper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        if (aType == 1) {
            maindb.execSQL("delete from Saw where _Key = " + key_s);
            maindb.close();
            helper.close();
            finish();
            getFragmentManager().popBackStack();
            System.gc();
            return;
        }
        try {
            Cursor cursor = maindb.rawQuery("select * from Saw where _Key = " + key_s, null);
            if (cursor.moveToFirst()) {
                maindb.close();
                helper.close();
                System.gc();
                return;
            }
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        maindb.execSQL("insert into Saw values (" + key_i + ")");
        view.setEnabled(false);
        maindb.close();
        helper.close();
        System.gc();
    }
}
