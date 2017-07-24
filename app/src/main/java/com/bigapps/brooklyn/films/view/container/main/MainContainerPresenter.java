package com.bigapps.brooklyn.films.view.container.main;

import com.bigapps.brooklyn.films.view.baseView.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Brooklyn on 07-Jul-17.
 */

public class MainContainerPresenter extends BasePresenter<MainContainerView> {

    @Inject
    public MainContainerPresenter() {
    }

    void loadData(){
        view.showInfo();
    }

    void moreNowPlayingClicked(){
        view.showNowPlayingFullList();
    }
}
