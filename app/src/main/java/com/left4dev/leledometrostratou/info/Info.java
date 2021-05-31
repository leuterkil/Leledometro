package com.left4dev.leledometrostratou.info;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.service.quicksettings.Tile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.home.Home;
import com.left4dev.leledometrostratou.ranks.Ranks;
import com.left4dev.leledometrostratou.spinners.army.TypeOfArmy;
import com.left4dev.leledometrostratou.spinners.army.TypeOfArmyAdapter;
import com.left4dev.leledometrostratou.spinners.corps.Corps;
import com.left4dev.leledometrostratou.spinners.corps.CorpsAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class Info extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private InfoViewModel mViewModel;
    private final Calendar calendar = Calendar.getInstance();
    private EditText dateOfDismissal,dateOfEnlistment,Name,Esso,Series;
    private DatePickerDialog.OnDateSetListener dateOfDismissalCale,dateOfEnlistmentCale;
    private CorpsAdapter corpsAdapter;
    private Datas datas = new Datas();
    private final Corps corps = new Corps();
    private Spinner corpsSpinner;
    private Button saveButton;

    private int ImageID;
    private String CorpTitle,Image;


    public static Info newInstance() {
        return new Info();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.info_fragment, container, false);

//        initialize Components
        dateOfDismissal = fragmentView.findViewById(R.id.editTextDateOfDismissal);
        dateOfEnlistment = fragmentView.findViewById(R.id.editTextDateOfEnlistment);
        Name = fragmentView.findViewById(R.id.editTextName);
        Esso = fragmentView.findViewById(R.id.editTextESSO);
        Series = fragmentView.findViewById(R.id.editTextSeira);
        saveButton = fragmentView.findViewById(R.id.buttonSave);
        corpsAdapter = new CorpsAdapter(getActivity(),corps.getTitles(),corps.getImages());
        corpsSpinner = fragmentView.findViewById(R.id.Corps);
        corpsSpinner.setAdapter(corpsAdapter);



        dateOfEnlistmentCale = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateOfEnlistment.setText(mViewModel.UpdateDateLabel(calendar));

            }
        };
        dateOfDismissalCale = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateOfDismissal.setText(mViewModel.UpdateDateLabel(calendar));

            }
        };

//      initialize Listeners
        saveButton.setOnClickListener(this);
        corpsSpinner.setOnItemSelectedListener(this);
        dateOfEnlistment.setOnClickListener(this);
        dateOfDismissal.setOnClickListener(this);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        File f = new File("/data/user/0/com.left4dev.leledometrostratou/files/personalData.xml");
        ArrayList<String> userDatas;
        if (f.exists())
        {
            userDatas = datas.loadXML(getActivity());
            Name.setText(userDatas.get(0));
            dateOfEnlistment.setText(userDatas.get(1));
            dateOfDismissal.setText(userDatas.get(2));
            Esso.setText(userDatas.get(3));
            Series.setText(userDatas.get(4));
            corpsSpinner.setSelection(Integer.parseInt(userDatas.get(7)));
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.editTextDateOfDismissal:
                new DatePickerDialog(getActivity(),dateOfDismissalCale, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.editTextDateOfEnlistment:
                new DatePickerDialog(getActivity(), dateOfEnlistmentCale, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.buttonSave:
                String name = Name.getText().toString();
                String esso = Esso.getText().toString();
                String series = Series.getText().toString();
                String doe = dateOfEnlistment.getText().toString();
                String dod = dateOfDismissal.getText().toString();
                datas.SaveChanges(getActivity(),name,doe,dod,esso,series,Image,CorpTitle,ImageID);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home()).commit();
                break;
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        setCorpTitle(corps.getTitle(i));
        setImage(Integer.toString(corps.getImage(i)));
        setImageID(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public void setCorpTitle(String corpTitle) {
        CorpTitle = corpTitle;
    }
    public void setImage(String image)
    {
        Image = image;
    }
}