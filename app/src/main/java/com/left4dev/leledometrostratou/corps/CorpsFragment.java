package com.left4dev.leledometrostratou.corps;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.home.Home;
import com.left4dev.leledometrostratou.recyclerviews.corps.CorpsRecyclerAdapter;

public class CorpsFragment extends Fragment {

    private CorpsViewModel mViewModel;
    private final com.left4dev.leledometrostratou.spinners.corps.Corps corps = new com.left4dev.leledometrostratou.spinners.corps.Corps();
    private CorpsRecyclerAdapter corpsRecyclerAdapter;
    private RecyclerView recyclerView;

    public static CorpsFragment newInstance() {
        return new CorpsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.corps_fragment, container, false);
        corpsRecyclerAdapter = new CorpsRecyclerAdapter(getActivity(),corps.getTitles(),corps.getImages());

        recyclerView = fragmentView.findViewById(R.id.corpsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        corpsRecyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(corpsRecyclerAdapter);


        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CorpsViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home()).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

}