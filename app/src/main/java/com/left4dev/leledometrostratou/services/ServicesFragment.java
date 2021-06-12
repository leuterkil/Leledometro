package com.left4dev.leledometrostratou.services;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.left4dev.leledometrostratou.home.Home;
import com.left4dev.leledometrostratou.tablists.ListOfServicesFragment;
import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.corps.CorpsFragment;
import com.left4dev.leledometrostratou.notebook.Notebook;
import com.left4dev.leledometrostratou.tablists.ListOfVacationFragment;

public class ServicesFragment extends Fragment {

    private ServicesViewModel mViewModel;
    private TabLayout tabs;

    public static ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.services_fragment, container, false);
        TabLayout tabLayout = (TabLayout) fragmentView.findViewById(R.id.tabs);
        final ViewPager viewPager = (ViewPager) fragmentView.findViewById(R.id.viewPager);

        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.getTabAt(0).setText("Υπηρεσίες");
        tabLayout.getTabAt(1).setText("Άδειες");
        tabLayout.getTabAt(2).setText("Ημερολόγιο");
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_service);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_adeia_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_services);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
    }
    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new ListOfServicesFragment();
                case 1:
                    return new ListOfVacationFragment();
                case 2:
                    return new CorpsFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
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