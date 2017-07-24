package com.bigapps.brooklyn.films.model.api;

import com.bigapps.brooklyn.films.model.entities.Credit;
import com.bigapps.brooklyn.films.model.entities.FilmDetails;
import com.bigapps.brooklyn.films.model.entities.PageFilm;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public interface DmdbApi {

    @GET("movie/{category}?api_key=0e1167c76e061f65cb570b74c42baf0f")
    Single<PageFilm> getFilmList(@Path("category") String category, @Query("page")int page);

    @GET("movie/{id}/similar?api_key=0e1167c76e061f65cb570b74c42baf0f")
    Single<PageFilm> getFilmList(@Path("id") int id, @Query("page")int page);

    @GET("movie/{id}?api_key=0e1167c76e061f65cb570b74c42baf0f")
    Single<FilmDetails> getFilmDetails(@Path("id") int id);

    @GET("movie/{id}/credits?api_key=0e1167c76e061f65cb570b74c42baf0f")
    Single<Credit> getFilmCredit(@Path("id") int id);



}
