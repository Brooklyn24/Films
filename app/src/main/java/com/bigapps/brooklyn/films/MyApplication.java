package com.bigapps.brooklyn.films;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.bigapps.brooklyn.films.di.components.AppComponent;
import com.bigapps.brooklyn.films.di.components.DaggerAppComponent;
import com.bigapps.brooklyn.films.di.modules.AppModule;

/**
 * Created by Brooklyn on 03-Jul-17.
 */

public class MyApplication extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
