package com.left4dev.leledometrostratou.notebook;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.left4dev.leledometrostratou.R;
import com.left4dev.leledometrostratou.functions.Datas;
import com.left4dev.leledometrostratou.home.Home;

import java.io.File;
import java.util.ArrayList;

public class Notebook extends Fragment implements View.OnClickListener {

    private NotebookViewModel mViewModel;

    private Button SaveButton;
    private EditText ASM,Id,Gun,Knife;
    private AdView adView;

    private final Datas datas = new Datas();
    private ArrayList<String> userNotes;

    public static Notebook newInstance() {
        return new Notebook();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.notebook_fragment, container, false);

        InitializeComponents(fragmentView);

        SaveButton.setOnClickListener(this);

        File f = new File(getString(R.string.notes_path));
        if (f.exists())
        {
            userNotes = datas.loadNotes(getActivity());
            ASM.setText(userNotes.get(0));
            Id.setText(userNotes.get(1));
            Gun.setText(userNotes.get(2));
            Knife.setText(userNotes.get(3));
        }


        return fragmentView;
    }

    private void InitializeComponents(View fragmentView) {
        SaveButton = fragmentView.findViewById(R.id.buttonSaveNotebook);
        ASM = fragmentView.findViewById(R.id.editTextASM);
        Id = fragmentView.findViewById(R.id.editTextIdNum);
        Gun = fragmentView.findViewById(R.id.editTextGunNum);
        Knife = fragmentView.findViewById(R.id.editTextKnifeNum);
        adView = fragmentView.findViewById(R.id.adViewNotes);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotebookViewModel.class);
        // TODO: Use the ViewModel
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonSaveNotebook:
                if (ASM.getText().toString().isEmpty()||Id.getText().toString().isEmpty()||Gun.getText().toString().isEmpty()
                ||Knife.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(),"Συμπληρώστε Όλα τα στοιχεία για να συνεχίσετε",Toast.LENGTH_LONG).show();
                }
                else
                {
                    datas.SaveNotes(getActivity(),ASM.getText().toString(),Id.getText().toString(),Gun.getText().toString(),
                            Knife.getText().toString());
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new Home()).commit();
                }
                break;

        }
    }
}