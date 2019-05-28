package com.coen268.moviemate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class NarBar extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    Intent secondIntent, goToHome, goSettings, goToProfile, goToSearch, loggedOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        secondIntent = getIntent();
        goToHome = new Intent(this, MoviesActivity.class);
        goSettings = new Intent(this, Settings.class);
        loggedOut = new Intent(this, SignInActivity.class);
        goToProfile = new Intent(this, ProfilePage.class);
        goToSearch = new Intent(this, SearchPage.class);

        dl = (DrawerLayout)findViewById(R.id.drawer);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.search_option:
                        startActivity(goToSearch);
                        break;
                    case R.id.discover:
                        startActivity(goToHome);
                        break;
                    case R.id.account:
                        startActivity(goToProfile);
                        //Toast.makeText(NarBar.this, "My Account",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        startActivity(goSettings);
                        //Toast.makeText(NarBar.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        loggedOut.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loggedOut);
                        break;
                    default:
                        return true;
                }

                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
