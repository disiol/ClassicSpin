package com.classicspin.wg.di.modules;

import com.classicspin.wg.di.scopes.ActivityScope;
import com.classicspin.wg.ui.activities.MainActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class, ApiModule.class, AppModule.class, ManagersModule.class})
public interface ApplicationModule {


    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    MainActivity mainActivityInjector();


}
