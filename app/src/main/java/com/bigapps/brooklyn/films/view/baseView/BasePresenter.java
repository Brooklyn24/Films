package com.bigapps.brooklyn.films.view.baseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public abstract class BasePresenter<T extends BaseView> {

    protected T view;
    @Inject CompositeDisposable disposable;

    protected void addDisposable(Disposable disposable) {
        this.disposable.add(disposable);
    }

    public void subscribe(T view){
        this.view = view;
    }

    public void unsubscribe(){
        disposable.clear();
        view = null;
    }

}
