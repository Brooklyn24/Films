package com.bigapps.brooklyn.films.di.modules;

import com.bigapps.brooklyn.films.MyApplication;
import com.bigapps.brooklyn.films.model.api.DmdbApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Brooklyn on 04-Jul-17.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    Cache provideHttpCache(MyApplication application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory rxJava2Adapter(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson,
                             OkHttpClient okHttpClient,
                             RxJava2CallAdapterFactory rxJava2Adapter) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(rxJava2Adapter)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    DmdbApi provideApi(Retrofit retrofit) {
        return retrofit.create(DmdbApi.class);
    }

}
