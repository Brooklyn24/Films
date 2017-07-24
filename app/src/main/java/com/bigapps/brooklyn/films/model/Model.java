package com.bigapps.brooklyn.films.model;

import com.bigapps.brooklyn.films.model.entities.Cast;
import com.bigapps.brooklyn.films.model.entities.Film;
import com.bigapps.brooklyn.films.model.entities.FilmDetails;
import com.bigapps.brooklyn.films.model.entities.PageFilm;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public interface Model {

    Single<List<Film>> getFilmList(int page, String category);

    Single<List<Film>> getFilmList(int page, int id);

    Single<FilmDetails> getFilmDetails(int id);

    Single<List<Cast>> getFilmCastList(int id);

}
