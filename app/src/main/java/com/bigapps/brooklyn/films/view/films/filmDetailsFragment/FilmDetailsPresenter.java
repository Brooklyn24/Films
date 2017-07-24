package com.bigapps.brooklyn.films.view.films.filmDetailsFragment;

import com.bigapps.brooklyn.films.model.Model;
import com.bigapps.brooklyn.films.view.baseView.BasePresenter;


import org.reactivestreams.Subscription;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public class FilmDetailsPresenter extends BasePresenter<FilmDetailsView> {

    @Inject Model model;

    @Inject
    public FilmDetailsPresenter() {
    }

    void loadData(int id) {
        view.showProgress();
        addDisposable(model.getFilmDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(film -> view.showFilm(film),
                        e -> view.showError("error")));
        view.hideProgress();
    }

}
