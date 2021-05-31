package com.left4dev.leledometrostratou.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.functions.Datas;

import java.util.ArrayList;

public class Home extends Fragment {

    private HomeViewModel mViewModel;
    private TextView headerTitle,headerSubtitle,headerESSO,headerSeries;
    private ImageView headerImage;
    private Datas data = new Datas();
    private ArrayList<String> userData;

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
        headerTitle = header.findViewById(R.id.headTitle);
        headerSubtitle = header.findViewById(R.id.headSubTitle);
        headerESSO = header.findViewById(R.id.textViewEsso);
        headerSeries = header.findViewById(R.id.textViewSeries);
        headerImage = header.findViewById(R.id.headerImage);
        userData = data.loadXML(getActivity());

        headerTitle.setText(userData.get(0));
        headerESSO.setText("ΕΣΣΟ: "+userData.get(3));
        headerSeries.setText("Σειρά: "+userData.get(4));
        headerSubtitle.setText(userData.get(6));
        headerImage.setImageResource(Integer.parseInt(userData.get(5)));
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

}