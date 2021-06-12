package com.left4dev.leledometrostratou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DocumentsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Dic,TiNaParo,Xaire,Para;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        InitializeComponents();

    }

    private void InitializeComponents()
    {
        Dic=findViewById(R.id.buttonDictionary);
        TiNaParo = findViewById(R.id.buttonTiNaParo);
        Xaire = findViewById(R.id.buttonXairetismoi);
        Para = findViewById(R.id.buttonParagelmata);
        mAdView = findViewById(R.id.adViewDocuments);
        Dic.setOnClickListener(this);
        TiNaParo.setOnClickListener(this);
        Xaire.setOnClickListener(this);
        Para.setOnClickListener(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonXairetismoi:
                Intent intentTiNaXaire = openLink(getString(R.string.xaire_link));
                startActivity(intentTiNaXaire);
                break;
            case R.id.buttonParagelmata:
                Intent intentPara = openLink(getString(R.string.para_link));
                startActivity(intentPara);
                break;
            case R.id.buttonDictionary:
                Intent intentDic = openLink(getString(R.string.dic_link));
                startActivity(intentDic);
                break;
            case R.id.buttonTiNaParo:
                Intent intentTiNaParo = openLink(getString(R.string.ti_na_paro_mazi_mou_ston_strato_link));
                startActivity(intentTiNaParo);
                break;
        }
    }

    private Intent openLink(String link) {
        Uri uri = Uri.parse(link); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return intent;
    }
}