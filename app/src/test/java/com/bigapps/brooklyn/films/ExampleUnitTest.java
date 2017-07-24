package com.bigapps.brooklyn.films;

import com.bigapps.brooklyn.films.di.modules.AppModule;
import com.bigapps.brooklyn.films.di.modules.ModelModule;
import com.bigapps.brooklyn.films.model.Model;
import com.bigapps.brooklyn.films.model.ModelImpl;
import com.bigapps.brooklyn.films.view.baseView.BaseView;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.FilmListFragment;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.FilmListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    FilmListFragment baseView;
    @Mock List list;
    FilmListPresenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new FilmListPresenter();
    }

    @Test
    public void mockTests1() throws Exception {
        assertNotNull(presenter);
    }

    @Test
    public void mockTests() throws Exception {
        baseView = Mockito.mock(FilmListFragment.class);
        presenter.subscribe(baseView);
        presenter.loadData(1, "top_rated");
        Mockito.verify(baseView).showProgress();
    }
}