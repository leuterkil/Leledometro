package com.left4dev.leledometrostratou.floatbuttonclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


import com.left4dev.leledometrostratou.MainActivity;
import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.spinners.vacation.Vacations;
import com.left4dev.leledometrostratou.spinners.vacation.VacationsAdapter;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

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
        toolbar = findViewById(R.id.toolbarVacations);
        saveButton = findViewById(R.id.buttonSaveVacations);
        startDate = findViewById(R.id.editTextDateOfVacationStart);
        endDate = findViewById(R.id.editTextDateOfVacationEnd);
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
                File f = new File("/data/user/0/com.left4dev.leledometrostratou/files/VacationsData.xml");
                String startDateString = startDate.getText().toString();
                String endDateString = endDate.getText().toString();
                if (!f.exists())
                {
                    datas.CreateVacationsFile(this,type,startDateString,endDateString);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    try {
                        datas.SaveChangesVacations(this,type,startDateString,endDateString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
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