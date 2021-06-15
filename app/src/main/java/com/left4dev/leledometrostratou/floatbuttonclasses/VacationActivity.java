package com.left4dev.leledometrostratou.floatbuttonclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


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
import com.left4dev.leledometrostratou.spinners.vacation.Vacations;
import com.left4dev.leledometrostratou.spinners.vacation.VacationsAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class VacationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner typeOfVacation;
    private EditText startDate,endDate;
    private final Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener datePickerStart,datePickerEnd;
    private VacationsAdapter adapter;
    private final VacationActivityFunctions vaf = new VacationActivityFunctions();
    private Toolbar toolbar;
    private Button saveButton;
    private Datas datas = new Datas();
    private Vacations vacations = new Vacations();
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private Date c = Calendar.getInstance().getTime();

    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation);
        InitializeComponents();

        typeOfVacation.setOnItemSelectedListener(this);
        saveButton.setOnClickListener(this);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
    }


    private void InitializeComponents()
    {
        typeOfVacation = findViewById(R.id.spinnerVacationType);
        mAdView = findViewById(R.id.adViewVacations);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd  MMM  yyyy", Locale.US);
        toolbar = findViewById(R.id.toolbarVacations);
        saveButton = findViewById(R.id.buttonSaveVacations);
        startDate = findViewById(R.id.editTextDateOfVacationStart);
        endDate = findViewById(R.id.editTextDateOfVacationEnd);
        startDate.setText(sdf.format(c));
        endDate.setText(sdf.format(c));
        adapter = new VacationsAdapter(this,vacations.getTypes());
        typeOfVacation.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbarVacations);
        toolbar.setTitle("Εισαγωγή Άδειας");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        datePickerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                startDate.setText(vaf.UpdateDateLabel(calendar));
            }
        };
        datePickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                endDate.setText(vaf.UpdateDateLabel(calendar));
            }
        };
    }


    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setType(vacations.getType(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonSaveVacations:
                File f = new File(getString(R.string.vacations_path));
                String startDateString = startDate.getText().toString();
                String endDateString = endDate.getText().toString();
                if (!f.exists())
                {
                    datas.CreateVacationsFile(this,type,startDateString,endDateString);
                    Intent intent = new Intent(this, MainActivity.class);
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(VacationActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    }
                    startActivity(intent);
                    finishAffinity();
                }
                else
                {
                    try {
                        datas.SaveChangesVacations(this,type,startDateString,endDateString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(this, MainActivity.class);
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(VacationActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    }
                    startActivity(intent);
                    finishAffinity();
                }
                break;

            case R.id.editTextDateOfVacationStart:
                new DatePickerDialog(this,datePickerStart, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.editTextDateOfVacationEnd:
                new DatePickerDialog(this,datePickerEnd, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}