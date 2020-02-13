package com.RaProject.whattosee;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private AdView adView;
    final static int Zas = 0;

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View navHeader = navigationView.getHeaderView(0);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            TextView twNavBarName = (TextView) navHeader.findViewById(R.id.textView);
            twNavBarName.setText(mAuth.getCurrentUser().getDisplayName());
            TextView twNavBarName1 = (TextView) navHeader.findViewById(R.id.textView1);
            twNavBarName1.setText(mAuth.getCurrentUser().getEmail());
        }
        else
        {
            TextView twNavBarName = (TextView) navHeader.findViewById(R.id.textView);
            twNavBarName.setText("");
            TextView twNavBarName1 = (TextView) navHeader.findViewById(R.id.textView1);
            twNavBarName1.setText("");
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movies, R.id.nav_serials, R.id.nav_anime,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.nav_shop, R.id.nav_cartoons, R.id.nav_done,
                R.id.nav_wanttosee)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}

        });

        adView = findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder()
                .build();


        // Start loading the ad in the background.
        adView.loadAd(adRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings :
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            NavigationView navigationView = findViewById(R.id.nav_view);
            View navHeader = navigationView.getHeaderView(0);
            TextView twNavBarName = (TextView) navHeader.findViewById(R.id.textView);
            twNavBarName.setText(mAuth.getCurrentUser().getDisplayName());
            TextView twNavBarName1 = (TextView) navHeader.findViewById(R.id.textView1);
            twNavBarName1.setText(mAuth.getCurrentUser().getEmail());
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
    public void onClick(View v) {
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        startActivity(intent);
    }



}
