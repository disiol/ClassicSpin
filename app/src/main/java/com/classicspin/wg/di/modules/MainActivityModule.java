package com.classicspin.wg.di.modules;


import com.classicspin.wg.di.scopes.ActivityScope;
import com.classicspin.wg.di.scopes.FragmentScope;
import com.classicspin.wg.routers.main.MainActivityRouter;
import com.classicspin.wg.routers.main.MainActivityRouterImpl;

import com.classicspin.wg.ui.fragments.start.view.StartFragment;
import com.classicspin.wg.ui.fragments.web.view.WebFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainActivityModule {
    @ActivityScope
    @Binds

    MainActivityRouter mainActivityRouter(MainActivityRouterImpl mainRouter);

    @FragmentScope
    @ContributesAndroidInjector
    WebFragment webFragment();

    @FragmentScope
    @ContributesAndroidInjector
    StartFragment logoFragment();


}
