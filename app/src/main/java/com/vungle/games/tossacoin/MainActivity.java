package com.vungle.games.tossacoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MaxAdListener, MaxRewardedAdListener {

    private MaxInterstitialAd interstitialAd;
    private MaxRewardedAd rewardedAd;

    private Context context;
    private String interstitialPlacementId,rewardPlacementId,mrecPlacementId,bannerPlacementId;
    private static String TAG = "MaxVungle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        initView();
    }

    public void initView() {
        Button initBtn = findViewById(R.id.init_btn);
        Button loadInterstitialBtn = findViewById(R.id.load_interstitial_btn);
        Button playInterstitialBtn = findViewById(R.id.play_interstitial_btn);
        Button loadRewardBtn = findViewById(R.id.load_reward_btn);
        Button playRewardBtn = findViewById(R.id.play_reward_btn);
        Button loadMrecBtn = findViewById(R.id.load_mrec_btn);
        Button loadBannerBtn = findViewById(R.id.load_banner_btn);

        initBtn.setOnClickListener(this);
        loadInterstitialBtn.setOnClickListener(this);
        playInterstitialBtn.setOnClickListener(this);
        loadRewardBtn.setOnClickListener(this);
        playRewardBtn.setOnClickListener(this);
        loadMrecBtn.setOnClickListener(this);
        loadBannerBtn.setOnClickListener(this);

        interstitialPlacementId = context.getString(R.string.interstitial_placement_id);
        rewardPlacementId = context.getString(R.string.reward_placement_id);
        mrecPlacementId = context.getString(R.string.mrec_placement_id);
        bannerPlacementId = context.getString(R.string.banner_placement_id);
    }

    public void init() {

        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(AppLovinSdkConfiguration config) {

            }
        } );
    }

    public void loadInterstitial() {
        interstitialAd = new MaxInterstitialAd( interstitialPlacementId, this );
        interstitialAd.setListener(this);

        // Load the first ad
        interstitialAd.loadAd();
    }

    public void playInterstitial() {
        if ( interstitialAd.isReady() )
        {
            interstitialAd.showAd();
        } else {
            Log.d(TAG, "The interstitial wasn't loaded yet.");
        }
    }

    public void loadReward() {
        rewardedAd = MaxRewardedAd.getInstance( rewardPlacementId, this );
        rewardedAd.setListener( this );

        rewardedAd.loadAd();
    }

    public void playReward() {
        if ( rewardedAd.isReady() )
        {
            rewardedAd.showAd();
        } else {
            Log.d(TAG, "The reward ad is not ready");
        }
    }

    public void loadMRECAd() {

    }

    public void loadBannerAd() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.init_btn:
                init();
                break;
            case R.id.load_interstitial_btn:
                loadInterstitial();
                break;
            case R.id.play_interstitial_btn:
                playInterstitial();
            case R.id.load_mrec_btn:
                loadMRECAd();
                break;
            case R.id.load_banner_btn:
                loadBannerAd();
                break;
            case R.id.load_reward_btn:
                loadReward();
                break;
            case R.id.play_reward_btn:
                playReward();
                break;
        }
    }

    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, int errorCode) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {
        loadInterstitial();

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, int errorCode) {

    }

    @Override
    public void onRewardedVideoStarted(MaxAd ad) {
        loadReward();

    }

    @Override
    public void onRewardedVideoCompleted(MaxAd ad) {

    }

    @Override
    public void onUserRewarded(MaxAd ad, MaxReward reward) {

    }
}

