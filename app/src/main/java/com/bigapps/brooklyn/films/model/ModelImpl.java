package com.bigapps.brooklyn.films.model;

import android.util.Log;

import com.bigapps.brooklyn.films.model.api.DmdbApi;
import com.bigapps.brooklyn.films.model.entities.Cast;
import com.bigapps.brooklyn.films.model.entities.Credit;
import com.bigapps.brooklyn.films.model.entities.Film;
import com.bigapps.brooklyn.films.model.entities.FilmDetails;
import com.bigapps.brooklyn.films.model.entities.PageFilm;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public class ModelImpl implements Model {

    public static final String TOP_RATED = "top_rated";
    public static final String NOW_PLAYING = "now_playing";
    public static final String POPULAR = "popular";

    @Inject DmdbApi dmdbApi;

    @Inject
    public ModelImpl() {
    }

    @Override
    public Single<List<Film>> getFilmList(int page, String category) {
        return dmdbApi.getFilmList(category, page)
                .subscribeOn(Schedulers.io())
                .map(pageFilm -> pageFilm.getFilms());
    }

    @Override
    public Single<List<Film>> getFilmList(int page, int id) {
        return dmdbApi.getFilmList(id, page)
                .subscribeOn(Schedulers.io())
                .map(pageFilm -> pageFilm.getFilms());
    }

    @Override
    public Single<FilmDetails> getFilmDetails(int id) {
        return dmdbApi.getFilmDetails(id)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<Cast>> getFilmCastList(int id) {
        return dmdbApi.getFilmCredit(id)
                .toObservable()
                .flatMap(credit -> Observable.fromIterable(credit.getCast()))
                .take(10)
                .toList()
                .subscribeOn(Schedulers.io());
    }
}
