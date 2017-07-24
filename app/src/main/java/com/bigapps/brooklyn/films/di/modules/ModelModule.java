package com.bigapps.brooklyn.films.di.modules;

import com.bigapps.brooklyn.films.model.Model;
import com.bigapps.brooklyn.films.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Brooklyn on 04-Jul-17.
 */
@Module
public class ModelModule {

    @Singleton
    @Provides
    Model model(ModelImpl modelImpl) {
        return modelImpl;
    }

}
