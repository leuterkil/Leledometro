package com.left4dev.leledometrostratou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.left4dev.leledometrostratou.about.About;
import com.left4dev.leledometrostratou.corps.Corps;
import com.left4dev.leledometrostratou.dictionary.Dictionary;
import com.left4dev.leledometrostratou.home.Home;
import com.left4dev.leledometrostratou.info.Info;
import com.left4dev.leledometrostratou.notebook.Notebook;
import com.left4dev.leledometrostratou.ranks.Ranks;
import com.left4dev.leledometrostratou.services.Services;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //MUST ENABLE NOACTIONBAR THEME IN MANIFEST FILE!!!!!

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home()).commit();

            navigationView.setCheckedItem(R.id.home);
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
                        new Corps()).commit();
                toolbar.setTitle(item.getTitle());
                break;

            case R.id.ranks:
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
            case R.id.services:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Services()).commit();
                toolbar.setTitle(item.getTitle());
                break;
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}