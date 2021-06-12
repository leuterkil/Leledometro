package com.left4dev.leledometrostratou.home;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.navigation.NavigationView;
import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.floatbuttonclasses.OutsActivity;
import com.left4dev.leledometrostratou.floatbuttonclasses.PenaltyMainActivity;
import com.left4dev.leledometrostratou.floatbuttonclasses.ServiceActivity;
import com.left4dev.leledometrostratou.floatbuttonclasses.VacationActivity;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.functions.PenaltyDatas;
import com.left4dev.leledometrostratou.functions.ServiceDatas;
import com.left4dev.leledometrostratou.functions.VacationDatas;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Home extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private HomeViewModel mViewModel;
    private TextView headerTitle,headerSubtitle,headerESSO,headerSeries,KS,Ypiretithikan,Synolo,Percent;
    private TextView NameOfSoldier,NameOfRank,TotalServices,TotalPrisons,TotalNoOuts,TotalVacations;
    private ImageView headerImage,SoldierImage,RankImage;
    private Datas data = new Datas();
    private ArrayList<String> userData;
    private ArrayList<ServiceDatas> userServices;
    private ArrayList<PenaltyDatas> userPenalties;
    private ArrayList<VacationDatas> userVacations;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton services,vacation,outs,penalty;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.home_fragment, container, false);
        NavigationView navigationView = getActivity().findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        toolbar = getActivity().findViewById(R.id.toolbar);
        navigationView.setCheckedItem(R.id.home);
        toolbar.setTitle(R.string.app_name);

//        initialize components
        InitializeComponents(fragmentView,header);
        userData = data.loadXML(getActivity());
        File f = new File(getString(R.string.services_path));
        File f2 = new File(getString(R.string.penalties_path));
        File f3 = new File(getString(R.string.vacations_path));


        try
        {
            if(f.exists())
            {
                userServices = data.loadServices(getActivity());
                TotalServices.setText(Integer.toString(userServices.size()));
            }
            else
            {
                TotalServices.setText("0");
            }
        } catch (IOException | XmlPullParserException e)
        {
            e.printStackTrace();
        }

        if (f2.exists())
        {
            try {
                userPenalties = data.loadPenalties(getActivity());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            String typeOfPenalty;
            int prisons=0,noOuts=0;
            for (int i=0;i<userPenalties.size();i++)
            {
                typeOfPenalty = userPenalties.get(i).getType();

                if (typeOfPenalty.equals("Φυλακή"))
                {
                    prisons = prisons + Integer.parseInt(userPenalties.get(0).getDays());
                }
                else
                {
                    noOuts = noOuts + Integer.parseInt(userPenalties.get(0).getDays());
                }
            }

            TotalPrisons.setText(Integer.toString(prisons));
            TotalNoOuts.setText(Integer.toString(noOuts));

        }
        else
        {
            TotalPrisons.setText("0");
            TotalNoOuts.setText("0");
        }

        if (f3.exists())
        {
            try {
                userVacations = data.loadVacations(getActivity());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd  MMM  yyyy",Locale.US);
            long TotalDaysOfVac = 0;
            for (int i=0;i<userVacations.size();i++)
            {
                Date dateStart = new Date(),dateEnd = new Date();
                try {
                     dateStart = sdf.parse(userVacations.get(i).getStartDate());
                     dateEnd = sdf.parse(userVacations.get(i).getEndDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long different = dateEnd.getTime() - dateStart.getTime();

                long secondsInMilli = 1000;
                long minutesInMilli = secondsInMilli * 60;
                long hoursInMilli = minutesInMilli * 60;
                long daysInMilli = hoursInMilli * 24;

                long elapsedDays = different / daysInMilli;
                TotalDaysOfVac = TotalDaysOfVac+elapsedDays;
            }
            TotalDaysOfVac+=2;
            TotalVacations.setText(Long.toString(TotalDaysOfVac));
        }
        else
        {
            TotalVacations.setText("0");
        }


        services.setOnClickListener(this);
        vacation.setOnClickListener(this);
        outs.setOnClickListener(this);
        penalty.setOnClickListener(this);
        TotalNoOuts.setOnLongClickListener(this);
        TotalPrisons.setOnLongClickListener(this);

        headerTitle.setText(userData.get(0));
        headerESSO.setText("ΕΣΣΟ: "+userData.get(3));
        headerSeries.setText("Σειρά: "+userData.get(4));
        headerSubtitle.setText(userData.get(6));
        headerImage.setImageResource(Integer.parseInt(userData.get(5)));
        NameOfSoldier.setText(userData.get(0));


        floatingActionMenu.setClosedOnTouchOutside(true);
        return fragmentView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String myFormat = "dd  MMM  yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date Start=new Date();
        Date End=new Date();
        Date c = Calendar.getInstance().getTime();
        try {
            Start = sdf.parse(userData.get(1));
            End = sdf.parse(userData.get(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        SoldierImage.setImageResource(mViewModel.CheckCorp(userData.get(6)));
        if (Integer.parseInt(mViewModel.KS(c,End))>Integer.parseInt(mViewModel.KS(Start,End)))
        {
            KS.setText((mViewModel.KS(Start, End)) + "ΚΣ");
        }
        else if (Integer.parseInt(mViewModel.KS(c,End))<0)
        {
           KS.setText("0ΚΣ");
        }
        else
        {
            KS.setText((mViewModel.KS(c, End)) + "ΚΣ");
        }




        if (Integer.parseInt(mViewModel.KS(Start,c))<0)
        {
            Ypiretithikan.setText("0");
        } else if (Integer.parseInt(mViewModel.KS(Start, c)) > Integer.parseInt(mViewModel.KS(Start, End))) {

            Ypiretithikan.setText(mViewModel.KS(Start,End));
        } else {
            Ypiretithikan.setText(mViewModel.KS(Start, c));
        }



        Synolo.setText(mViewModel.KS(Start,End));


        float synolo,ypiretithikan;
        synolo = Float.parseFloat(mViewModel.KS(Start,End));
        ypiretithikan = Float.parseFloat(mViewModel.KS(Start,c));
        float percent = (ypiretithikan/synolo)*100;
        DecimalFormat df = new DecimalFormat("0.0");



        if (percent<0.0)
        {
            Percent.setText("0.0%");
            progressBar.setProgress(0);
        }
        else if (percent>100.00)
        {
            Percent.setText("100.0%");
            progressBar.setProgress(100);
        }
        else {
            Percent.setText(df.format(percent) + "%");
            progressBar.setProgress((int) percent);
        }

        RankImage.setImageResource(mViewModel.CheckRank(percent));
        NameOfRank.setText(mViewModel.CheckRankName(percent,userData.get(6)));
    }

    private void InitializeComponents(View fragmentView,View header)
    {
        headerTitle = header.findViewById(R.id.headTitle);
        TotalServices = fragmentView.findViewById(R.id.textViewServiceTotal);
        TotalPrisons = fragmentView.findViewById(R.id.textViewPrisons);
        TotalNoOuts = fragmentView.findViewById(R.id.textViewNoOuts);
        KS = fragmentView.findViewById(R.id.textViewMeres);
        Ypiretithikan = fragmentView.findViewById(R.id.textViewYpiretithikan);
        Percent = fragmentView.findViewById(R.id.textViewPercentage);
        Synolo = fragmentView.findViewById(R.id.textViewSynolo);
        progressBar = fragmentView.findViewById(R.id.progressBar);
        headerSubtitle = header.findViewById(R.id.headSubTitle);
        headerESSO = header.findViewById(R.id.textViewEsso);
        TotalVacations = fragmentView.findViewById(R.id.textViewRepoDays);
        headerSeries = header.findViewById(R.id.textViewSeries);
        headerImage = header.findViewById(R.id.headerImage);
        floatingActionMenu = fragmentView.findViewById(R.id.floatMenu);
        penalty = floatingActionMenu.findViewById(R.id.Floatpenalty);
        outs = floatingActionMenu.findViewById(R.id.Floatout);
        vacation = floatingActionMenu.findViewById(R.id.Floatvacation);
        services = floatingActionMenu.findViewById(R.id.Floatservices);
        SoldierImage = fragmentView.findViewById(R.id.imageViewTyposSkoufou);
        NameOfSoldier = fragmentView.findViewById(R.id.textViewNameOfSoldier);
        NameOfRank = fragmentView.findViewById(R.id.textViewRankNameHome);
        RankImage = fragmentView.findViewById(R.id.imageViewRankHome);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Floatservices:
                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.Floatpenalty:
                Intent intentPenalties = new Intent(getActivity(), PenaltyMainActivity.class);
                startActivity(intentPenalties);
                break;
            case R.id.Floatvacation:
                Intent intentVacations = new Intent(getActivity(), VacationActivity.class);
                startActivity(intentVacations);
                break;
            case R.id.Floatout:
                Intent intentOuts = new Intent(getActivity(), OutsActivity.class);
                startActivity(intentOuts);
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId())
        {
            case R.id.textViewPrisons:
                if (TotalPrisons.getText().toString().equals("0"))
                {
                    return true;
                }
                else
                {
                    AlertDialog alertDialog = mViewModel.confirm(getActivity(),TotalNoOuts.getText().toString(),data
                            ,"Στέρηση Εξόδου",TotalPrisons);
                    alertDialog.show();
                }
                break;
            case R.id.textViewNoOuts:
                if (TotalNoOuts.getText().toString().equals("0"))
                {
                    return true;
                }
                else
                {
                    AlertDialog alertDialog = mViewModel.confirm(getActivity(),TotalPrisons.getText().toString(),data
                            ,"Φυλακή",TotalNoOuts);
                    alertDialog.show();
                }
                break;
        }
        return true;
    }
}