package com.bigapps.brooklyn.films.di.modules;

import com.bigapps.brooklyn.films.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Brooklyn on 03-Jul-17.
 */
@Module
public class AppModule {

    MyApplication app;

    public AppModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    MyApplication setApp() {
        return app;
    }



    @Provides
    CompositeDisposable disposable() {
        return new CompositeDisposable();
    }

}
