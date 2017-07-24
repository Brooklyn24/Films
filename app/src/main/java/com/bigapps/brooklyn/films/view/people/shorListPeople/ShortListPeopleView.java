package com.bigapps.brooklyn.films.view.people.shorListPeople;

import com.bigapps.brooklyn.films.model.entities.Cast;
import com.bigapps.brooklyn.films.view.baseView.BaseView;

import java.util.List;

/**
 * Created by Brooklyn on 06-Jul-17.
 */

public interface ShortListPeopleView extends BaseView {

    void showCastList(List<Cast> castList);

}
