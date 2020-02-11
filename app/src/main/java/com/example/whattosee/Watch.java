package com.example.whattosee;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

/** Main Activity. Inflates main activity xml. */
public class Watch extends AppCompatActivity {
    private AdView adView1;
    private static final String AD_UNIT_ID = "ca-app-pub-9595963256137742/6406704403";
    private static final long COUNTER_TIME = 10;
    private static final int GAME_OVER_REWARD = 1;

    private int coinCount;
    private int a;
    private TextView coinCountText;
    private CountDownTimer countDownTimer;
    private boolean gameOver;
    private boolean gamePaused;
    private RewardedVideoAd mRewardedVideoAd;
    private RewardedAd rewardedAd;
    private Button retryButton;
    private Button showVideoButton;
    private long timeRemaining;
    boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView1 = findViewById(R.id.ad_view2);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Start loading the ad in the background.
        adView1.loadAd(adRequest);
        loadRewardedAd();

        // Create the "retry" button, which tries to show a rewarded ad between game plays.
        retryButton = findViewById(R.id.retry_button);
        retryButton.setVisibility(View.INVISIBLE);
        retryButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startGame();
                    }
                });

        // Create the "show" button, which shows a rewarded video if one is loaded.
        showVideoButton = findViewById(R.id.show_video_button);
        showVideoButton.setVisibility(View.INVISIBLE);
        showVideoButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showRewardedVideo();
                        if (mRewardedVideoAd.isLoaded()) {
                            mRewardedVideoAd.show();
                        }
                    }
                });

        // Display current coin count to user.
        coinCountText = findViewById(R.id.coin_count_text);
        coinCount = 0;
        coinCountText.setText("Coins: " + coinCount);

        startGame();
    }

    @Override
    public void onDestroy() {
        if (adView1 != null) {
            adView1.destroy();
        }
        mRewardedVideoAd.destroy(this);
        super.onDestroy();

    }

    @Override
    public void onPause() {
        if (adView1 != null) {
            adView1.pause();
        }
        super.onPause();
        mRewardedVideoAd.pause(this);
        pauseGame();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!gameOver && gamePaused) {
            resumeGame();
        }
        if (adView1 != null) {
            adView1.resume();
        }
        mRewardedVideoAd.resume(this);
    }

    private void pauseGame() {
        countDownTimer.cancel();
        gamePaused = true;
    }

    private void resumeGame() {
        createTimer(timeRemaining);
        gamePaused = false;
    }

    private void loadRewardedAd() {
        if (rewardedAd == null || !rewardedAd.isLoaded()) {
            rewardedAd = new RewardedAd(this, AD_UNIT_ID);
            isLoading = true;
            rewardedAd.loadAd(
                    new AdRequest.Builder().build(),
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onRewardedAdLoaded() {
                            // Ad successfully loaded.
                            Watch.this.isLoading = false;
                            Toast.makeText(Watch.this, "onRewardedAdLoaded", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onRewardedAdFailedToLoad(int errorCode) {
                            // Ad failed to load.
                            Watch.this.isLoading = false;
                            Toast.makeText(Watch.this, "onRewardedAdFailedToLoad", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
        }
    }

    private void addCoins(int coins) {
        coinCount += coins;
        coinCountText.setText("Coins: " + coinCount);
    }

    private void startGame() {
        // Hide the retry button, load the ad, and start the timer.
        retryButton.setVisibility(View.INVISIBLE);
        showVideoButton.setVisibility(View.INVISIBLE);
        mRewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        if (!rewardedAd.isLoaded() && !isLoading) {
            loadRewardedAd();
        }
        createTimer(COUNTER_TIME);
        gamePaused = false;
        gameOver = false;
    }

    // Create the game timer, which counts down to the end of the level
    // and shows the "retry" button.
    private void createTimer(long time) {
        final TextView textView = findViewById(R.id.timer);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer =
                new CountDownTimer(time * 500, 50) {
                    @Override
                    public void onTick(long millisUnitFinished) {
                        timeRemaining = ((millisUnitFinished / 1000) + 1);
                        textView.setText("Подождите пока появиться реклама: " + timeRemaining);
                    }

                    @Override
                    public void onFinish() {
                        if (rewardedAd.isLoaded()) {
                            showVideoButton.setVisibility(View.VISIBLE);
                        }
                        textView.setText("Спасибо за ожидание!");
                        addCoins(GAME_OVER_REWARD);

                            retryButton.setVisibility(View.VISIBLE);

                        gameOver = true;
                    }
                };
        countDownTimer.start();
    }

    private void showRewardedVideo() {
        showVideoButton.setVisibility(View.INVISIBLE);
        a = coinCount;
        if (rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback =
                    new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            // Ad opened.
                            Toast.makeText(Watch.this, "onRewardedAdOpened", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            // Ad closed.
                            Toast.makeText(Watch.this, "onRewardedAdClosed", Toast.LENGTH_SHORT).show();
                            // Preload the next video ad.
                            Watch.this.loadRewardedAd();
                            loadRewardedAd();
                        }

                        @Override
                        public void onUserEarnedReward(RewardItem rewardItem) {
                            // User earned reward.
                            Toast.makeText(Watch.this, "onUserEarnedReward", Toast.LENGTH_SHORT).show();
                            addCoins(100);
                            if(coinCount == a || coinCount == (a + 1) )
                            {
                                addCoins(100);
                            }
                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {
                            // Ad failed to display
                            Toast.makeText(Watch.this, "onRewardedAdFailedToShow", Toast.LENGTH_SHORT)
                                    .show();
                        }



                    };
            rewardedAd.show(this, adCallback);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}