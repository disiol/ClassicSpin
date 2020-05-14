package com.classicspin.wg.di.modules;

import com.classicspin.wg.api.Api;
import com.classicspin.wg.interactor.DataStore;
import com.google.gson.Gson;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    DataStore provideDataStore(Api api, Gson gson) {
        return new DataStore(api, gson);
    }
}
