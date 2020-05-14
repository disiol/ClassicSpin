package com.classicspin.wg.di.modules;

import android.content.Context;


import com.classicspin.wg.manedger.PreferencesManager;
import com.classicspin.wg.manedger.PreferencesManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagersModule {

    @Provides
    @Singleton
    PreferencesManager providePreferencesManager(Context context){
        return new PreferencesManagerImpl(context);
    }


}
