package com.bigapps.brooklyn.films.view.people.shorListPeople;

import com.bigapps.brooklyn.films.model.Model;
import com.bigapps.brooklyn.films.view.baseView.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Brooklyn on 06-Jul-17.
 */

public class ShortListPeoplePresenter extends BasePresenter<ShortListPeopleView> {

    @Inject Model model;

    @Inject
    public ShortListPeoplePresenter() {
    }

    void loadData(int id){
        addDisposable(model.getFilmCastList(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> view.showCastList(list)));
    }


}
