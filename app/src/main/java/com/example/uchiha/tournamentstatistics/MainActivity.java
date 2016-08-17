package com.example.uchiha.tournamentstatistics;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by uchiha on 16.8.2016.
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        // Setup drawer view
        setupDrawerContent(nvDrawer);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                }
        );
    }

    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(item.getItemId()) {

            case R.id.nav_history:
                fragmentClass = HistoryActivity.class;
                break;

            case R.id.nav_statistics:
                fragmentClass = StatisticsActivity.class;
                break;

            case R.id.nav_options:
                fragmentClass = OptionsActivity.class;
                break;

            default:
                fragmentClass = HistoryActivity.class;
        }
        try {

            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {

            e.printStackTrace();

        }

        FragmentManager frManager = getSupportFragmentManager();
        frManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawer.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
