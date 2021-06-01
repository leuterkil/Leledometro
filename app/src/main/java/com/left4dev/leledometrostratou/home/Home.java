package com.left4dev.leledometrostratou.home;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

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
import com.left4dev.leledometrostratou.functions.Datas;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

public class Home extends Fragment {

    private HomeViewModel mViewModel;
    private TextView headerTitle,headerSubtitle,headerESSO,headerSeries,KS,Ypiretithikan,Synolo,Percent;
    private TextView NameOfSoldier,NameOfRank;
    private ImageView headerImage,SoldierImage,RankImage;
    private Datas data = new Datas();
    private ArrayList<String> userData;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton services,vacation,outs,penalty;
    private ProgressBar progressBar;

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.home_fragment, container, false);
        NavigationView navigationView = getActivity().findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);

//        initialize components
        InitializeComponents(fragmentView,header);
        userData = data.loadXML(getActivity());

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
        KS = fragmentView.findViewById(R.id.textViewMeres);
        Ypiretithikan = fragmentView.findViewById(R.id.textViewYpiretithikan);
        Percent = fragmentView.findViewById(R.id.textViewPercentage);
        Synolo = fragmentView.findViewById(R.id.textViewSynolo);
        progressBar = fragmentView.findViewById(R.id.progressBar);
        headerSubtitle = header.findViewById(R.id.headSubTitle);
        headerESSO = header.findViewById(R.id.textViewEsso);
        headerSeries = header.findViewById(R.id.textViewSeries);
        headerImage = header.findViewById(R.id.headerImage);
        floatingActionMenu = fragmentView.findViewById(R.id.floatMenu);
        penalty = floatingActionMenu.findViewById(R.id.penalty);
        outs = floatingActionMenu.findViewById(R.id.out);
        vacation = floatingActionMenu.findViewById(R.id.vacation);
        services = floatingActionMenu.findViewById(R.id.services);
        SoldierImage = fragmentView.findViewById(R.id.imageViewTyposSkoufou);
        NameOfSoldier = fragmentView.findViewById(R.id.textViewNameOfSoldier);
        NameOfRank = fragmentView.findViewById(R.id.textViewRankNameHome);
        RankImage = fragmentView.findViewById(R.id.imageViewRankHome);
    }
}