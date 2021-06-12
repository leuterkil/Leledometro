package com.left4dev.leledometrostratou.floatbuttonclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.left4dev.leledometrostratou.MainActivity;
import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.spinners.penalty.Penalty;
import com.left4dev.leledometrostratou.spinners.penalty.PenaltyAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class PenaltyMainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner typePenaltySpinner,daysOfPenaltySpinner;
    private PenaltyAdapter adapter;
    private Button saveButton;
    private ArrayList<String> Days = new ArrayList<>();
    private Penalty penalty = new Penalty();
    private Datas datas = new Datas();
    private Toolbar toolbar;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private String type;
    private int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty_main);
        InitializeComponents();
        saveButton.setOnClickListener(this);
        typePenaltySpinner.setOnItemSelectedListener(this);
        daysOfPenaltySpinner.setOnItemSelectedListener(this);
    }


    private void InitializeComponents()
    {
        toolbar = findViewById(R.id.toolbarPenalties);
        mAdView = findViewById(R.id.adViewPenalties);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("TAG", "The ad was dismissed.");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
        toolbar.setTitle("Εισαγωγή Ποινής");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        typePenaltySpinner = findViewById(R.id.spinnerTypeOfPenalty);
        daysOfPenaltySpinner = findViewById(R.id.spinnerCountOfPenalty);
        saveButton = findViewById(R.id.buttonSavePenalty);


        for (int i=1;i<=99;i++)
        {
            Days.add(i+" μέρες");
        }
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_days_of_penalty,Days);
        daysOfPenaltySpinner.setAdapter(dayAdapter);
        adapter = new PenaltyAdapter(this,penalty.getTypes());
        typePenaltySpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.spinnerTypeOfPenalty:
                setType(penalty.getType(position));
                break;
            case R.id.spinnerCountOfPenalty:
                String dayparse = Days.get(position);
                String daysparseNew= dayparse.replace(" μέρες","");
                setDay(Integer.parseInt(daysparseNew));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonSavePenalty:
                File f = new File(getString(R.string.penalties_path));
                if (f.exists())
                {
                    try {
                        datas.SavePenalties(this,type,Integer.toString(day));
                        Intent intent = new Intent(this, MainActivity.class);
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(PenaltyMainActivity.this);
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                        startActivity(intent);
                        finishAffinity();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    datas.CreatePenaltiesFile(this,type,Integer.toString(day));
                    Intent intent = new Intent(this, MainActivity.class);
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(PenaltyMainActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    }
                    startActivity(intent);
                    finishAffinity();
                }
                break;
        }
    }

    public String getType() {
        return type;
    }

    public int getDay() {
        return day;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}