package com.bigapps.brooklyn.films.view.films.filmsListFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigapps.brooklyn.films.MyApplication;
import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.adapters.FilmClickListener;
import com.bigapps.brooklyn.films.adapters.LongFilmListAdapter;
import com.bigapps.brooklyn.films.model.entities.Film;
import com.bigapps.brooklyn.films.view.MainContainerCallbacks;
import com.bigapps.brooklyn.films.view.baseView.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FilmListFragment extends BaseFragment implements FilmListView,
        FilmClickListener {

    @Inject FilmListPresenter presenter;
    protected String category;
    @BindView(R.id.filmListRV) RecyclerView filmListRV;
    protected LongFilmListAdapter longFilmListAdapter;

    public FilmListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getComponent().inject(this);
        category = getArguments().getString("category");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public void showFilmList(List<Film> films) {
        longFilmListAdapter = new LongFilmListAdapter(films, getActivity());
        longFilmListAdapter.setFilmClickListener(this);
        filmListRV.setAdapter(longFilmListAdapter);
    }

    @Override
    public void showFilmDetailsFragment(int id) {
        ((MainContainerCallbacks.ShowFilmDetails)containerActivity).showFilmDetails(id);
    }

    @Override
    public void onFilmClicked(int id) {
        presenter.filmListClicked(id);
    }

}
