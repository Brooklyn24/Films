package com.bigapps.brooklyn.films.view;

/**
 * Created by Brooklyn on 04-Jul-17.
 */

public interface MainContainerCallbacks {

    void showProgress();
    void hideProgress();
    void showError(String error);

    interface ShowFilmDetails{
        void showFilmDetails(int id);
    }

    interface ShowFilmList{
        void showPopular();
        void showNowPlaying();
        void showTopRated();
    }

}
