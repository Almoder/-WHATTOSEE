package com.RaProject.whattosee;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {


    private AdView adView;
    private List<ContentContainer> items = new ArrayList();
    ListView countriesList;
    String INF;
    int IDnTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_activ);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setInitialData();
        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);
        // создаем адаптер
        ContentAdapter stateAdapter = new ContentAdapter(this, R.layout.conetent_view, items);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        adView = findViewById(R.id.ad_view3);

        AdRequest adRequest = new AdRequest.Builder()
                .build();

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
        Intent intent = getIntent();
        INF = intent.getStringExtra("Part");
        System.out.println(INF);

        items.add(new ContentContainer ("ВАЛЛ·И", "ВАЛЛ·И", "2008", "США","Фантаскика, Приключения, Семейный",
                "92 мин", "8.4", "8.3", "Эндрю Стэнтон", vali(), " Бен Бертт, Элисса Найт, Джефф Гарлин, Фред Уиллард, Джон Ратценбергер",
                R.drawable.vali));
        //items.add(new Items ("Корпорация монстров", "-", R.mipmap.ic_launcher2));
        //items.add(new Items ("Труп Невесты", "-", R.mipmap.ic_launcher2));

    }
}
