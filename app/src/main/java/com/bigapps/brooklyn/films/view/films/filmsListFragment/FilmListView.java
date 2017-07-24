package com.bigapps.brooklyn.films.view.films.filmsListFragment;

import com.bigapps.brooklyn.films.model.entities.Film;
import com.bigapps.brooklyn.films.view.baseView.BaseView;

import java.util.List;

/**
 * Created by Brooklyn on 03-Jul-17.
 */

public interface FilmListView extends BaseView {

    void showFilmList(List<Film> films);

    void showFilmDetailsFragment(int id);

}
