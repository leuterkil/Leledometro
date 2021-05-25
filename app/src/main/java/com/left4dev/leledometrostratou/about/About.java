package com.left4dev.leledometrostratou.about;

import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.left4dev.leledometrostratou.DonateActivity;
import com.left4dev.leledometrostratou.R;

public class About extends Fragment implements View.OnClickListener {

    private AboutViewModel mViewModel;
    private Button instagramButton,buttonFB,donateButton,buttonEmail;

    public static About newInstance() {
        return new About();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.about_fragment, container, false);

        //initialize components
        instagramButton = (Button) fragmentView.findViewById(R.id.instagramButton);
        donateButton = (Button) fragmentView.findViewById(R.id.donationButton);
        buttonFB = (Button) fragmentView.findViewById(R.id.buttonFB);
        buttonEmail = (Button) fragmentView.findViewById(R.id.buttonEmail);
        //initialize click listeners
        buttonFB.setOnClickListener(this);
        buttonEmail.setOnClickListener(this);
        donateButton.setOnClickListener(this);
        instagramButton.setOnClickListener(this);

        return fragmentView;
    }


    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId())
        {
            case R.id.buttonFB:
                intent = mViewModel.openLink("facebook.com");
                startActivity(intent);
                break;
            case R.id.buttonEmail:
                intent = mViewModel.sendMail(getString(R.string.app_email));

                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                startActivity(intent);
                break;
            case R.id.donationButton:
                intent = new Intent(getActivity(),DonateActivity.class);
                startActivity(intent);
                break;
            case R.id.instagramButton:
                intent = mViewModel.openLink("instagram.com/left4dev/");
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);

    }


}