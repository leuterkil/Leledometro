package com.left4dev.leledometrostratou.tablists;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.expandableviews.ServiceExpandAdapter;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.functions.ServiceDatas;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.left4dev.leledometrostratou.functions.Datas.groupBy;

public class ListOfServicesFragment extends Fragment implements View.OnClickListener {

    private ListOfServicesViewModel mViewModel;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private Datas datas = new Datas();
    private Button DeleteAll;

    public static ListOfServicesFragment newInstance() {
        return new ListOfServicesFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_of_services_fragment, container, false);

        expListView = fragmentView.findViewById(R.id.lvExp);
        DeleteAll = fragmentView.findViewById(R.id.buttonDeleteAllServices);

        File f = new File("/data/user/0/com.left4dev.leledometrostratou/files/ServicesData.xml");
        if (f.exists()) {
            try {
                if (!datas.loadServices(getActivity()).isEmpty()) {
                    prepareListData();
                    listAdapter = new ServiceExpandAdapter(getActivity(), listDataHeader, listDataChild);
                    expListView.setAdapter(listAdapter);
                }
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
        }

        DeleteAll.setOnClickListener(this);
        // setting list adapter


        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListOfServicesViewModel.class);
        // TODO: Use the ViewModel
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        ArrayList<ServiceDatas> test = new ArrayList<>();
        try {
            test = datas.loadServices(getActivity());

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        Map<String, List<ServiceDatas>> map = groupBy(test,ServiceDatas::getType);
        ArrayList<String> temp = new ArrayList<>(map.keySet());


        // Adding child data
      //  listDataHeader.add(map.get());

        List<List<ServiceDatas>> values = new ArrayList<>(map.values());
        for (int p = 0;p<map.size();p++)
        {
            listDataHeader.add(temp.get(p)+ "   ("+values.get(p).size()+")");
        }

        // Adding child data
        for (int i=0;i<map.size();i++)
        {
            List<ServiceDatas> data = values.get(i);
            List<String> numbers = new ArrayList<>();
            for (int j=0;j<data.size();j++)
            {
                numbers.add(data.get(j).getTime()+" - "+data.get(j).getDate());

            }
            listDataChild.put(listDataHeader.get(i), numbers);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonDeleteAllServices:
                AlertDialog alertDialog = mViewModel.confirm(getActivity());
                alertDialog.show();
                break;
        }
    }

}