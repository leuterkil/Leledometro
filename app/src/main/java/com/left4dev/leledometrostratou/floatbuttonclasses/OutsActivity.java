package com.left4dev.leledometrostratou.floatbuttonclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.left4dev.leledometrostratou.MainActivity;
import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.functions.Datas;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OutsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText dateOfOuts;
    private final Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener datePicker;
    private OutsActivityFunctions oaf = new OutsActivityFunctions();
    private Toolbar toolbar;
    private Button saveButton;
    private Datas datas = new Datas();
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outs);
        InitializeComponents();


        saveButton.setOnClickListener(this);
        dateOfOuts.setOnClickListener(this);

        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateOfOuts.setText(oaf.UpdateDateLabel(calendar));
            }
        };
    }

    private void InitializeComponents()
    {
        saveButton = findViewById(R.id.buttonSaveOuts);
        toolbar = findViewById(R.id.toolbarOuts);
        mAdView = findViewById(R.id.adViewOuts);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        toolbar.setTitle("Εισαγωγή Εξόδου");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dateOfOuts = findViewById(R.id.editTextDateOfOut);
        String myFormat = "dd  MMM  yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date c = Calendar.getInstance().getTime();
        dateOfOuts.setText(sdf.format(c.getTime()));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonSaveOuts:
                File f = new File(getString(R.string.outs_path));
                if (f.exists())
                {
                    if (dateOfOuts.getText().toString().isEmpty())
                    {
                        Toast.makeText(this,"Πρέπει να συμπληρώσεις όλα τα δεδομένα",Toast.LENGTH_LONG).show();
                    }
                    else {
                        try {
                            datas.SaveOuts(this, dateOfOuts.getText().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intentMain = new Intent(OutsActivity.this, MainActivity.class);
                        startActivity(intentMain);
                        finishAffinity();
                    }
                }
                else
                {
                    datas.CreateOutsFile(this,dateOfOuts.getText().toString());
                    Intent intentMain = new Intent(OutsActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    finishAffinity();
                }
                break;
            case R.id.editTextDateOfOut:
                new DatePickerDialog(this,datePicker, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}