package com.bigapps.brooklyn.films.di.components;

import com.bigapps.brooklyn.films.di.modules.AppModule;
import com.bigapps.brooklyn.films.di.modules.ModelModule;
import com.bigapps.brooklyn.films.di.modules.NetworkModule;
import com.bigapps.brooklyn.films.view.container.main.MainContainerFragment;
import com.bigapps.brooklyn.films.view.films.filmDetailsFragment.FilmDetailsFragment;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.FilmListFragment;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.ShortFilmListFragment;
import com.bigapps.brooklyn.films.view.people.shorListPeople.ShortListPeopleFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Brooklyn on 03-Jul-17.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ModelModule.class})
public interface AppComponent {

    void inject(FilmListFragment fragment);
    void inject(FilmDetailsFragment fragment);
    void inject(ShortListPeopleFragment fragment);
    void inject(ShortFilmListFragment fragment);
    void inject(MainContainerFragment fragment);
}
