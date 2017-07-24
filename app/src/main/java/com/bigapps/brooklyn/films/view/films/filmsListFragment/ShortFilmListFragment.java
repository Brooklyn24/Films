package com.bigapps.brooklyn.films.view.films.filmsListFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.adapters.FilmClickListener;
import com.bigapps.brooklyn.films.adapters.ShortFilmLIstAdapter;
import com.bigapps.brooklyn.films.model.entities.Film;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShortFilmListFragment extends FilmListFragment implements FilmListView,
        FilmClickListener {

    ShortFilmLIstAdapter shortFilmListAdapter;
    @BindView(R.id.progressPB) ProgressBar progressPB;

    public ShortFilmListFragment() {
    }

    public static ShortFilmListFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("category", category);
        ShortFilmListFragment fragment = new ShortFilmListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_short_film_list, container, false);
        ButterKnife.bind(this, view);
        presenter.subscribe(this);
        presenter.loadData(1, category);
        return view;
    }

    @Override
    public void showFilmList(List<Film> films) {
        filmListRV.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        shortFilmListAdapter = new ShortFilmLIstAdapter(films, getActivity());
        shortFilmListAdapter.setFilmClickListener(this);
        filmListRV.setAdapter(shortFilmListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public void showProgress() {
        progressPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressPB.setVisibility(View.INVISIBLE);
    }
}
