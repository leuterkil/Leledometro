package com.left4dev.leledometrostratou.floatbuttonclasses;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
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
import com.left4dev.leledometrostratou.functions.ServiceDatas;
import com.left4dev.leledometrostratou.spinners.services.NumbersAdapter;
import com.left4dev.leledometrostratou.spinners.services.Services;
import com.left4dev.leledometrostratou.spinners.services.ServicesAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    private Spinner typeOfServiceSpinner,typeOfNumberSpinner;
    private EditText dateOfService;
    private final Calendar calendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener datePickerService;
    private final Services services = new Services();
    private NumbersAdapter numbersAdapter;
    private ServicesAdapter servicesAdapter;
    private final ServiceActivityFunctions saf = new ServiceActivityFunctions();
    private Datas datas = new Datas();
    private Toolbar toolbar;
    private Button SaveButton;

    private String num,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        InitializeComponents();

        typeOfServiceSpinner.setOnItemSelectedListener(this);
        typeOfNumberSpinner.setOnItemSelectedListener(this);
        dateOfService.setOnClickListener(this);

    }


    private void InitializeComponents()
    {
        typeOfServiceSpinner = findViewById(R.id.spinnerServices);
        typeOfNumberSpinner = findViewById(R.id.spinnerNumber);
        SaveButton = findViewById(R.id.buttonSaveServices);
        dateOfService = findViewById(R.id.editTextDateOfService);
        numbersAdapter = new NumbersAdapter(this,services.getNumbers());
        servicesAdapter = new ServicesAdapter(this,services.getServicesTypes());
        typeOfNumberSpinner.setAdapter(numbersAdapter);
        typeOfServiceSpinner.setAdapter(servicesAdapter);

        toolbar = findViewById(R.id.toolbarServices);
        toolbar.setTitle("Εισαγωγή Υπηρεσίας");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SaveButton.setOnClickListener(this);

        datePickerService = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateOfService.setText(saf.UpdateDateLabel(calendar));
            }
        };

    }



    public void setNum(String num1)
    {
        num = num1;
    }

    public void setType(String type1)
    {
        type = type1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.spinnerServices:
                setType(services.getServiceType(position));
                break;
            case R.id.spinnerNumber:
                setNum(services.getNumber(position));
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
            case R.id.editTextDateOfService:
                new DatePickerDialog(this,datePickerService, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.buttonSaveServices:
                File f = new File(String.valueOf(R.string.services_path));
                String date = dateOfService.getText().toString();
                if (!f.exists())
                {
                    datas.CreateServicesFile(this,type,num,date);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    try {
                        datas.SaveServices(this,type,num,date);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}


