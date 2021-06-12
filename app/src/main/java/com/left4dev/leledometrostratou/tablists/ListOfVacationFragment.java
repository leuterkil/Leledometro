package com.left4dev.leledometrostratou.tablists;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.expandableviews.ServiceExpandAdapter;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.functions.VacationDatas;
import com.left4dev.leledometrostratou.recyclerviews.listvacations.ListVacationsAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfVacationFragment extends Fragment implements View.OnClickListener {

    private ListOfVacationViewModel mViewModel;
    private RecyclerView recyclerView;
    private ListVacationsAdapter listVacationsAdapter;
    private Datas datas = new Datas();
    private List<VacationDatas> vacationDatasList;
    private Button deleteAll;

    public static ListOfVacationFragment newInstance() {
        return new ListOfVacationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_of_vacation_fragment, container, false);

        deleteAll = fragmentView.findViewById(R.id.buttonDeleteAllVacations);

        File f = new File(getString(R.string.vacations_path));
        if (f.exists()) {
            try {
                if (!datas.loadVacations(getActivity()).isEmpty()) {
                    deleteAll.setOnClickListener(this);
                    recyclerView = fragmentView.findViewById(R.id.vacationsRecycler);
                    vacationDatasList = datas.loadVacations(getActivity());
                    String[] startDates = new String[vacationDatasList.size()], endDates = new String[vacationDatasList.size()], Types = new String[vacationDatasList.size()];
                    for (int i = 0; i < vacationDatasList.size(); i++) {
                        startDates[i] = vacationDatasList.get(i).getStartDate();
                        endDates[i] = vacationDatasList.get(i).getEndDate();
                        Types[i] = vacationDatasList.get(i).getType();
                    }
                    listVacationsAdapter = new ListVacationsAdapter(getActivity(),Types, startDates, endDates);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    listVacationsAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(listVacationsAdapter);
                }
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
        }


        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListOfVacationViewModel.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonDeleteAllVacations:
                AlertDialog dialog = mViewModel.confirm(getActivity(),getActivity());
                dialog.show();
                break;
        }
    }
}