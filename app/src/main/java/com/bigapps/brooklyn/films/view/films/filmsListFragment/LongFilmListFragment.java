package com.bigapps.brooklyn.films.view.films.filmsListFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.adapters.LongFilmListAdapter;
import com.bigapps.brooklyn.films.model.entities.Film;

import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LongFilmListFragment extends FilmListFragment {


    public LongFilmListFragment() {
    }

    public static LongFilmListFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("category", category);
        LongFilmListFragment fragment = new LongFilmListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_long_film_list, container, false);
        ButterKnife.bind(this, view);
        presenter.subscribe(this);
        presenter.loadData(1, category);
        return view;
    }

    @Override
    public void showFilmList(List<Film> films) {
        filmListRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        longFilmListAdapter = new LongFilmListAdapter(films, getActivity());
        longFilmListAdapter.setFilmClickListener(this);
        filmListRV.setAdapter(longFilmListAdapter);
    }

}
