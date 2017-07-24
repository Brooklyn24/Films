package com.bigapps.brooklyn.films.view;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.model.ModelImpl;
import com.bigapps.brooklyn.films.view.container.main.MainContainerFragment;
import com.bigapps.brooklyn.films.view.films.filmDetailsFragment.FilmDetailsFragment;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.FilmListFragment;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.LongFilmListFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class MainContainerActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, MainContainerCallbacks,
        MainContainerCallbacks.ShowFilmDetails, MainContainerCallbacks.ShowFilmList {

    private ActionBarDrawerToggle toggle;
    ProgressDialog mProgressDialog;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nvView) NavigationView nvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgressDialog = new ProgressDialog(this);

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        nvView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerFl, MainContainerFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass = null;
        switch (item.getItemId()) {

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.containerFl, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void showProgress() {
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(getString(R.string.progress_bar_loading_alert));
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFilmDetails(int id) {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.containerFl, FilmDetailsFragment.newInstance(id))
                .commit();
    }

    @Override
    public void showPopular() {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.containerFl, LongFilmListFragment.newInstance(ModelImpl.POPULAR))
                .commit();
    }

    @Override
    public void showNowPlaying() {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.containerFl, LongFilmListFragment.newInstance(ModelImpl.NOW_PLAYING))
                .commit();
    }

    @Override
    public void showTopRated() {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.containerFl, LongFilmListFragment.newInstance(ModelImpl.TOP_RATED))
                .commit();
    }
}