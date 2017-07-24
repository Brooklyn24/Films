package com.bigapps.brooklyn.films.view.films.filmsListFragment;

import com.bigapps.brooklyn.films.model.Model;
import com.bigapps.brooklyn.films.view.baseView.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Brooklyn on 03-Jul-17.
 */

public class FilmListPresenter extends BasePresenter<FilmListView> {

    @Inject Model model;

    @Inject
    public FilmListPresenter() {
    }

    public void filmListClicked(int id) {
        view.showFilmDetailsFragment(id);
    }

    public void loadData(int id, String category) {
        //TODO pagination
        view.showProgress();
        addDisposable(model.getFilmList(id, category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            view.showFilmList(list);
                            view.hideProgress();
                        },
                        e -> {
                            e.printStackTrace();
                            view.hideProgress();
                        }));

    }


}
