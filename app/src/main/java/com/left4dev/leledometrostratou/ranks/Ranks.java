package com.left4dev.leledometrostratou.ranks;

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

import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.recyclerviews.corps.CorpsRecyclerAdapter;
import com.left4dev.leledometrostratou.recyclerviews.ranks.RanksDatas;
import com.left4dev.leledometrostratou.recyclerviews.ranks.RanksRecyclerAdapter;

public class Ranks extends Fragment {

    private RanksViewModel mViewModel;
    private RecyclerView ranksRecyclerView;
    private RanksRecyclerAdapter adapter;
    private RanksDatas datas = new RanksDatas();

    public static Ranks newInstance() {
        return new Ranks();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.ranks_fragment, container, false);

//        initialize components
        adapter = new RanksRecyclerAdapter(getActivity(),datas.getNames(),datas.getImages());

        ranksRecyclerView = fragmentView.findViewById(R.id.ranksRecyclerView);
        ranksRecyclerView.setHasFixedSize(true);
        ranksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.notifyDataSetChanged();
        ranksRecyclerView.setAdapter(adapter);


        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RanksViewModel.class);
    }

}