package com.bigapps.brooklyn.films.view.films.filmDetailsFragment;

import com.bigapps.brooklyn.films.model.entities.FilmDetails;
import com.bigapps.brooklyn.films.view.baseView.BaseView;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public interface FilmDetailsView extends BaseView {

    void showFilm(FilmDetails filmDetails);

}
