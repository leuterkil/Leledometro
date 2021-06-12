package com.left4dev.leledometrostratou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.transition.Explode;
import android.view.MenuItem;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.left4dev.leledometrostratou.about.About;
import com.left4dev.leledometrostratou.corps.CorpsFragment;
import com.left4dev.leledometrostratou.dictionary.Dictionary;
import com.left4dev.leledometrostratou.home.Home;
import com.left4dev.leledometrostratou.info.Info;
import com.left4dev.leledometrostratou.notebook.Notebook;
import com.left4dev.leledometrostratou.ranks.Ranks;
import com.left4dev.leledometrostratou.services.ServicesFragment;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //MUST ENABLE NOACTIONBAR THEME IN MANIFEST FILE!!!!!

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        File f = new File(getString(R.string.personal_info_path));
        if (f.exists())
        {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home()).commit();

                navigationView.setCheckedItem(R.id.home);
            }
        }
        else
        {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Info()).commit();

                navigationView.setCheckedItem(R.id.info);
            }
        }

    }

//    this override method selects a menu item and shows the fragment
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Info()).commit();
                toolbar.setTitle(item.getTitle());
                break;

            case R.id.dictionary:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Dictionary()).commit();
                toolbar.setTitle(item.getTitle());
                break;

            case R.id.corps:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CorpsFragment()).commit();
                toolbar.setTitle(item.getTitle());
                break;

            case R.id.ranksRecyclerView:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Ranks()).commit();
                toolbar.setTitle(item.getTitle());
                break;

            case R.id.about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new About()).commit();
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home()).commit();
                toolbar.setTitle(R.string.app_name);
                break;
            case R.id.notebook:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Notebook()).commit();
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.Floatservices:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ServicesFragment()).commit();
                toolbar.setTitle(item.getTitle());
                break;
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}